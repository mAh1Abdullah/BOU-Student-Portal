const http = require('http');
const fs = require('fs');
const path = require('path');
const {
  OFFICIAL_SERVICES,
  OFFICIAL_CONTACTS,
  REGIONAL_CENTERS,
  PROGRAM_TEMPLATES
} = require('./src/data/bouData');
const {
  BOU_GRADES,
  marksToGrade,
  gradeNameToGrade,
  calculateSemesterGPA,
  calculateCumulativeCGPA,
  calculateRequiredGPA,
  getDegreeClassification
} = require('./src/calculator');

const PORT = 3000;
const HOST = '0.0.0.0';

const MIME_TYPES = {
  '.html': 'text/html; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.js': 'application/javascript; charset=utf-8',
  '.json': 'application/json; charset=utf-8',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.svg': 'image/svg+xml',
  '.ico': 'image/x-icon',
  '.webp': 'image/webp'
};

const server = http.createServer((req, res) => {
  // CORS headers
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  if (req.method === 'OPTIONS') {
    res.writeHead(204);
    res.end();
    return;
  }

  // Health endpoint for proxy
  if (req.url === '/health' || req.url === '/api/health') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      status: 'ok',
      product: 'BOU Student Companion',
      version: '1.2.0',
      time: new Date().toISOString()
    }));
    return;
  }

  // API: Grading Standard
  if (req.url === '/api/grading-standard') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(BOU_GRADES));
    return;
  }

  // API: Official Services Registry
  if (req.url === '/api/services') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(OFFICIAL_SERVICES));
    return;
  }

  // API: Contacts & Regional Centers Directory
  if (req.url === '/api/contacts') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      contacts: OFFICIAL_CONTACTS,
      regionalCenters: REGIONAL_CENTERS
    }));
    return;
  }

  // API: Program Curriculum Templates
  if (req.url === '/api/programs') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(PROGRAM_TEMPLATES));
    return;
  }

  // API: Calculation Engine POST
  if (req.url === '/api/calculate' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => { body += chunk; });
    req.on('end', () => {
      try {
        const payload = JSON.parse(body || '{}');
        const { courses, semesters, targetPlanning } = payload;
        const result = {};

        if (Array.isArray(courses)) {
          result.semester = calculateSemesterGPA(courses);
        }
        if (Array.isArray(semesters)) {
          result.cumulative = calculateCumulativeCGPA(semesters);
        }
        if (targetPlanning) {
          result.targetPlan = calculateRequiredGPA(
            targetPlanning.currentCGPA,
            targetPlanning.completedCredits,
            targetPlanning.remainingCredits,
            targetPlanning.targetCGPA
          );
        }

        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify(result));
      } catch (err) {
        res.writeHead(400, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'Invalid JSON payload' }));
      }
    });
    return;
  }

  // Normalize URL path
  let reqPath = req.url.split('?')[0];
  if (reqPath === '/' || reqPath === '') {
    reqPath = '/index.html';
  }

  const filePath = path.join(__dirname, 'public', reqPath);

  // Check if file exists in public/
  fs.stat(filePath, (err, stats) => {
    if (err || !stats.isFile()) {
      // Fallback to public/index.html for single-page routing
      const indexPath = path.join(__dirname, 'public', 'index.html');
      fs.readFile(indexPath, (readErr, content) => {
        if (readErr) {
          res.writeHead(404, { 'Content-Type': 'text/plain' });
          res.end('404 Not Found');
          return;
        }
        res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end(content);
      });
      return;
    }

    const ext = path.extname(filePath).toLowerCase();
    const contentType = MIME_TYPES[ext] || 'application/octet-stream';

    fs.readFile(filePath, (readErr, content) => {
      if (readErr) {
        res.writeHead(500, { 'Content-Type': 'text/plain' });
        res.end('500 Internal Server Error');
        return;
      }
      res.writeHead(200, { 'Content-Type': contentType });
      res.end(content);
    });
  });
});

server.listen(PORT, HOST, () => {
  console.log(`[BOU Portal] Web Dev Server listening on http://${HOST}:${PORT}`);
});

process.on('SIGTERM', () => {
  console.log('[BOU Portal] Received SIGTERM, shutting down...');
  server.close(() => {
    process.exit(0);
  });
});
