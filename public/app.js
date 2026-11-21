// BOU Student Companion - Client Application Logic
// Follows /PLAN.md: Independent Student Companion, Deterministic Calculations, Bilingual i18n

const BOU_GRADES = [
  { min: 80, grade: 'A+', point: 4.00, remarkEn: 'Outstanding', remarkBn: 'অসাধারণ' },
  { min: 75, grade: 'A',  point: 3.75, remarkEn: 'Excellent', remarkBn: 'চমৎকার' },
  { min: 70, grade: 'A-', point: 3.50, remarkEn: 'Very Good', remarkBn: 'অনেক ভালো' },
  { min: 65, grade: 'B+', point: 3.25, remarkEn: 'Good', remarkBn: 'ভালো' },
  { min: 60, grade: 'B',  point: 3.00, remarkEn: 'Satisfactory', remarkBn: 'সন্তোষজনক' },
  { min: 55, grade: 'B-', point: 2.75, remarkEn: 'Above Average', remarkBn: 'গড়ের উপরে' },
  { min: 50, grade: 'C+', point: 2.50, remarkEn: 'Average', remarkBn: 'গড়' },
  { min: 45, grade: 'C',  point: 2.25, remarkEn: 'Below Average', remarkBn: 'গড়ের নিচে' },
  { min: 40, grade: 'C-', point: 2.00, remarkEn: 'Pass', remarkBn: 'উত্তীর্ণ (পাস)' },
  { min: 0,  grade: 'F',  point: 0.00, remarkEn: 'Fail', remarkBn: 'অনুত্তীর্ণ' }
];

const I18N = {
  en: {
    appTitle: 'BOU Student Companion',
    appSubtitle: 'Independent Student Utility Dashboard • Bangladesh Open University',
    disclaimer: 'Independent Student Utility Dashboard. Official records, examination results, admissions, and notices are provided exclusively by Bangladesh Open University through its official systems.',
    tabHub: 'Portal Hub',
    tabCalc: 'Academic Calculator',
    tabProgress: 'Degree Progress',
    tabProfile: 'OSAPS Profile',
    tabContacts: 'Helpline & Directory',
    officialBadge: 'Official BOU Service',
    utilityBadge: 'Student Utility',
    marksInput: 'Marks (0–100)',
    letterGradeInput: 'Letter Grade',
    addCourse: 'Add Course',
    clearAll: 'Clear All',
    copyId: 'Copy Student ID',
    saveProfile: 'Save Profile Locally',
    clearProfile: 'Clear Saved Data',
    targetPlanner: 'Required GPA Target Planner',
    calculateTarget: 'Calculate Target Feasibility'
  },
  bn: {
    appTitle: 'বাউবি স্টুডেন্ট কম্প্যানিয়ন',
    appSubtitle: 'বাংলাদেশ উন্মুক্ত বিশ্ববিদ্যালয় শিক্ষার্থীদের স্বাধীন তথ্য ও একাডেমিক পোর্টাল',
    disclaimer: 'এটি একটি স্বাধীন শিক্ষার্থী সহযোগী পোর্টাল। সকল অফিসিয়াল একাডেমিক তথ্য, পরীক্ষার ফলাফল, ভর্তি ও নোটিশ সরাসরি বাংলাদেশ উন্মুক্ত বিশ্ববিদ্যালয়ের অফিসিয়াল সিস্টেম কর্তৃক পরিচালিত হয়।',
    tabHub: 'সার্ভিস হাব',
    tabCalc: 'একাডেমিক ক্যালকুলেটর',
    tabProgress: 'ডিগ্রি প্রগ্রেস',
    tabProfile: 'ওএসএপিএস প্রোফাইল',
    tabContacts: 'হেল্পলাইন ও কেন্দ্র',
    officialBadge: 'অফিসিয়াল বাউবি সার্ভিস',
    utilityBadge: 'শিক্ষার্থী ইউটিলিটি',
    marksInput: 'প্রাপ্ত নম্বর (০–১০০)',
    letterGradeInput: 'লেটার গ্রেড',
    addCourse: 'কোর্স যুক্ত করুন',
    clearAll: 'রিসেট করুন',
    copyId: 'আইডি কপি করুন',
    saveProfile: 'ডিভাইসে সংরক্ষণ করুন',
    clearProfile: 'তথ্য মুছুন',
    targetPlanner: 'লক্ষ্য অর্জনে প্রয়োজনীয় জিপিএ',
    calculateTarget: 'সম্ভাব্যতা যাচাই করুন'
  }
};

const state = {
  lang: 'en',
  activeTab: 'hub',
  calcMode: 'marks',
  programTotalCredits: 120,
  courses: [
    { id: 1, title: 'Principles of Management (BBA 1101)', credits: 3.0, marks: 82, grade: 'A+' },
    { id: 2, title: 'Business Communication (BBA 1102)', credits: 3.0, marks: 77, grade: 'A' },
    { id: 3, title: 'Financial Accounting (BBA 1103)', credits: 3.0, marks: 72, grade: 'A-' },
    { id: 4, title: 'Business Mathematics (BBA 1104)', credits: 3.0, marks: 68, grade: 'B+' },
    { id: 5, title: 'Computer Applications in Business (BBA 1105)', credits: 3.0, marks: 85, grade: 'A+' }
  ],
  semesters: [
    { id: 1, name: 'Semester 1', credits: 15, gpa: 3.50 },
    { id: 2, name: 'Semester 2', credits: 15, gpa: 3.65 }
  ],
  profile: {
    studentId: '21-0-11-020-045',
    name: 'Md. Abdullah Al Mamun',
    program: 'Bachelor of Business Administration (BBA)',
    session: '2021-2022',
    rc: 'Dhaka Regional Center (RC 11)',
    studyCenter: 'Govt. Bangla College, Mirpur, Dhaka'
  }
};

function toggleLanguage() {
  state.lang = (state.lang === 'en') ? 'bn' : 'en';
  document.getElementById('lang-toggle-btn').innerText = (state.lang === 'en') ? 'বাংলা' : 'English';
  applyLocalization();
}

function applyLocalization() {
  const dict = I18N[state.lang];
  document.querySelectorAll('[data-i18n]').forEach(el => {
    const key = el.getAttribute('data-i18n');
    if (dict[key]) {
      el.innerText = dict[key];
    }
  });
  renderCourses();
  recalculateStats();
  recalculateCumulative();
}

function marksToGrade(marks) {
  const num = Number(marks) || 0;
  for (const item of BOU_GRADES) {
    if (num >= item.min) return item;
  }
  return BOU_GRADES[BOU_GRADES.length - 1];
}

function gradeNameToGrade(gradeStr) {
  const match = BOU_GRADES.find(g => g.grade === gradeStr);
  return match || { grade: gradeStr || 'F', point: 0.0, remarkEn: 'Unknown', remarkBn: 'অজানা' };
}

function switchTab(tabKey) {
  state.activeTab = tabKey;
  ['hub', 'calc', 'progress', 'profile', 'contacts'].forEach(key => {
    const el = document.getElementById('tab-' + key);
    const btn = document.getElementById('nav-btn-' + key);
    if (el) {
      if (key === tabKey) el.classList.remove('hidden');
      else el.classList.add('hidden');
    }
    if (btn) {
      if (key === tabKey) {
        btn.className = 'active-nav-pill px-3.5 py-2 rounded-lg font-semibold text-xs transition-all flex items-center space-x-1.5';
      } else {
        btn.className = 'text-slate-600 hover:text-slate-900 px-3.5 py-2 rounded-lg font-semibold text-xs transition-all flex items-center space-x-1.5';
      }
    }
  });
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

function setCalcMode(mode) {
  state.calcMode = mode;
  const btnMarks = document.getElementById('mode-marks');
  const btnGrade = document.getElementById('mode-grade');
  const thHeader = document.getElementById('th-input-header');

  if (mode === 'marks') {
    btnMarks.className = 'px-3 py-1.5 rounded-lg bg-bou-green text-white shadow-sm transition-all';
    btnGrade.className = 'px-3 py-1.5 rounded-lg text-slate-600 hover:text-slate-900 transition-all';
    thHeader.innerText = (state.lang === 'en') ? 'Marks (0–100)' : 'প্রাপ্ত নম্বর (০–১০০)';
  } else {
    btnGrade.className = 'px-3 py-1.5 rounded-lg bg-bou-green text-white shadow-sm transition-all';
    btnMarks.className = 'px-3 py-1.5 rounded-lg text-slate-600 hover:text-slate-900 transition-all';
    thHeader.innerText = (state.lang === 'en') ? 'Letter Grade' : 'লেটার গ্রেড';
  }
  renderCourses();
}

function addCourseRow() {
  const nextId = (state.courses.length > 0 ? Math.max(...state.courses.map(c => c.id)) : 0) + 1;
  state.courses.push({
    id: nextId,
    title: 'Course ' + nextId,
    credits: 3.0,
    marks: 75,
    grade: 'A'
  });
  renderCourses();
}

function removeCourseRow(id) {
  if (state.courses.length <= 1) {
    showToast(state.lang === 'en' ? 'At least one course is required' : 'নূন্যতম একটি কোর্স আবশ্যক');
    return;
  }
  state.courses = state.courses.filter(c => c.id !== id);
  renderCourses();
}

function updateCourseField(id, field, value) {
  const course = state.courses.find(c => c.id === id);
  if (!course) return;

  if (field === 'title') {
    course.title = value;
  } else if (field === 'credits') {
    course.credits = Math.max(0.5, Math.min(12, parseFloat(value) || 3.0));
  } else if (field === 'marks') {
    const m = Math.max(0, Math.min(100, parseFloat(value) || 0));
    course.marks = m;
    course.grade = marksToGrade(m).grade;
  } else if (field === 'grade') {
    course.grade = value;
    const match = BOU_GRADES.find(g => g.grade === value);
    if (match) course.marks = match.min + 2;
  }
  recalculateStats();
}

function recalculateStats() {
  let totalAttempted = 0;
  let totalEarned = 0;
  let totalQualityPoints = 0;
  let failedCount = 0;

  state.courses.forEach(c => {
    const gradeObj = (state.calcMode === 'marks') ? marksToGrade(c.marks) : gradeNameToGrade(c.grade);
    const cr = parseFloat(c.credits) || 0;
    totalAttempted += cr;

    if (gradeObj.grade !== 'F') {
      totalEarned += cr;
    } else {
      failedCount++;
    }

    totalQualityPoints += cr * gradeObj.point;

    const gradeCell = document.getElementById('row-grade-' + c.id);
    const gpCell = document.getElementById('row-gp-' + c.id);
    if (gradeCell) {
      gradeCell.innerHTML = `<span class="px-2 py-0.5 rounded font-bold text-xs ${gradeObj.grade === 'F' ? 'bg-rose-100 text-bou-crimson' : 'bg-emerald-100 text-bou-green'}">${gradeObj.grade}</span>`;
    }
    if (gpCell) {
      gpCell.innerText = gradeObj.point.toFixed(2);
    }
  });

  const gpa = totalAttempted > 0 ? (totalQualityPoints / totalAttempted) : 0;
  const cgpaEl = document.getElementById('stat-cgpa');
  const classEl = document.getElementById('stat-class');
  const tcEl = document.getElementById('stat-total-credits');
  const ecEl = document.getElementById('stat-earned-credits');
  const fcEl = document.getElementById('stat-failed-count');
  const badgeEl = document.getElementById('stat-badge');

  if (cgpaEl) cgpaEl.innerText = gpa.toFixed(2);
  if (tcEl) tcEl.innerText = totalAttempted.toFixed(1);
  if (ecEl) ecEl.innerText = totalEarned.toFixed(1);
  if (fcEl) {
    fcEl.innerText = (state.lang === 'en')
      ? `${failedCount} Failed courses`
      : `${failedCount} টি অনুত্তীর্ণ কোর্স`;
  }

  let classText = '';
  let badgeClass = '';
  if (totalAttempted === 0) {
    classText = (state.lang === 'en') ? 'Awaiting courses' : 'কোর্সের অপেক্ষায়';
    badgeClass = 'bg-slate-100 text-slate-700';
  } else if (gpa >= 3.75) {
    classText = (state.lang === 'en') ? 'First Class with Distinction' : 'ডিস্টিংকশন সহ প্রথম শ্রেণি';
    badgeClass = 'bg-emerald-100 text-emerald-800 font-extrabold';
  } else if (gpa >= 3.00) {
    classText = (state.lang === 'en') ? 'First Class / First Division' : 'প্রথম শ্রেণি / বিভাগ';
    badgeClass = 'bg-blue-100 text-blue-900 font-bold';
  } else if (gpa >= 2.25) {
    classText = (state.lang === 'en') ? 'Second Class / Second Division' : 'দ্বিতীয় শ্রেণি / বিভাগ';
    badgeClass = 'bg-amber-100 text-amber-900 font-bold';
  } else if (gpa >= 2.00) {
    classText = (state.lang === 'en') ? 'Pass Division' : 'পাস বিভাগ';
    badgeClass = 'bg-slate-100 text-slate-800 font-medium';
  } else {
    classText = (state.lang === 'en') ? 'Unsatisfactory (Probation)' : 'অসন্তোষজনক (প্রবেশন)';
    badgeClass = 'bg-rose-100 text-bou-crimson font-bold';
  }

  if (classEl) classEl.innerText = classText;
  if (badgeEl) {
    badgeEl.innerText = classText;
    badgeEl.className = 'text-xs font-bold mt-2 inline-flex items-center px-2.5 py-1 rounded-md ' + badgeClass;
  }
}

function renderCourses() {
  const tbody = document.getElementById('course-rows');
  if (!tbody) return;

  tbody.innerHTML = state.courses.map(c => {
    const gradeObj = (state.calcMode === 'marks') ? marksToGrade(c.marks) : gradeNameToGrade(c.grade);

    const inputControl = (state.calcMode === 'marks')
      ? `<input type="number" min="0" max="100" value="${c.marks}" oninput="updateCourseField(${c.id}, 'marks', this.value)" class="w-full px-2 py-1.5 rounded border border-slate-300 font-mono text-xs focus:ring-1 focus:ring-bou-green focus:border-bou-green"/>`
      : `<select onchange="updateCourseField(${c.id}, 'grade', this.value)" class="w-full px-2 py-1.5 rounded border border-slate-300 font-bold text-xs bg-white focus:ring-1 focus:ring-bou-green focus:border-bou-green">
          ${BOU_GRADES.map(g => `<option value="${g.grade}" ${g.grade === c.grade ? 'selected' : ''}>${g.grade} (${g.point.toFixed(2)})</option>`).join('')}
         </select>`;

    return `
      <tr class="hover:bg-slate-50 transition-colors">
        <td class="py-2.5 px-3">
          <input type="text" value="${c.title}" oninput="updateCourseField(${c.id}, 'title', this.value)" class="w-full px-2 py-1 rounded border border-slate-200 text-xs text-slate-800 font-medium focus:ring-1 focus:ring-bou-green focus:border-bou-green bg-transparent"/>
        </td>
        <td class="py-2.5 px-3">
          <input type="number" step="0.5" min="1" max="6" value="${c.credits}" oninput="updateCourseField(${c.id}, 'credits', this.value)" class="w-20 px-2 py-1 rounded border border-slate-300 font-mono text-xs focus:ring-1 focus:ring-bou-green focus:border-bou-green"/>
        </td>
        <td class="py-2.5 px-3">
          ${inputControl}
        </td>
        <td class="py-2.5 px-3" id="row-grade-${c.id}">
          <span class="px-2 py-0.5 rounded font-bold text-xs ${gradeObj.grade === 'F' ? 'bg-rose-100 text-bou-crimson' : 'bg-emerald-100 text-bou-green'}">${gradeObj.grade}</span>
        </td>
        <td class="py-2.5 px-3 font-mono font-bold text-slate-700" id="row-gp-${c.id}">
          ${gradeObj.point.toFixed(2)}
        </td>
        <td class="py-2.5 px-2 text-center">
          <button onclick="removeCourseRow(${c.id})" class="p-1 rounded text-slate-400 hover:text-bou-crimson hover:bg-rose-50 transition-colors" title="Delete Course">
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/></svg>
          </button>
        </td>
      </tr>
    `;
  }).join('');

  recalculateStats();
}

function loadProgramPreset(presetKey) {
  if (presetKey === 'custom') return;

  const presets = {
    bba: [
      { id: 1, title: 'Principles of Management (BBA 1101)', credits: 3.0, marks: 80, grade: 'A+' },
      { id: 2, title: 'Business Communication (BBA 1102)', credits: 3.0, marks: 76, grade: 'A' },
      { id: 3, title: 'Financial Accounting (BBA 1103)', credits: 3.0, marks: 72, grade: 'A-' },
      { id: 4, title: 'Business Mathematics (BBA 1104)', credits: 3.0, marks: 68, grade: 'B+' },
      { id: 5, title: 'Computer Applications in Business (BBA 1105)', credits: 3.0, marks: 84, grade: 'A+' }
    ],
    ba_bss: [
      { id: 1, title: 'Bangla Language & Literature (BA 111)', credits: 3.0, marks: 75, grade: 'A' },
      { id: 2, title: 'English Foundation Course (BA 112)', credits: 3.0, marks: 66, grade: 'B+' },
      { id: 3, title: 'History of Bengal (BA 113)', credits: 3.0, marks: 73, grade: 'A-' },
      { id: 4, title: 'Political Science Basics (BA 114)', credits: 3.0, marks: 70, grade: 'A-' },
      { id: 5, title: 'Islamic Studies / Philosophy (BA 115)', credits: 3.0, marks: 78, grade: 'A' },
      { id: 6, title: 'Bangladesh Studies (BA 116)', credits: 3.0, marks: 82, grade: 'A+' }
    ],
    llb: [
      { id: 1, title: 'Jurisprudence & Legal Theory (LLB 101)', credits: 4.0, marks: 74, grade: 'A-' },
      { id: 2, title: 'Law of Contract (LLB 102)', credits: 4.0, marks: 76, grade: 'A' },
      { id: 3, title: 'Constitutional Law of Bangladesh (LLB 103)', credits: 4.0, marks: 81, grade: 'A+' },
      { id: 4, title: 'Muslim Law & Personal Law (LLB 104)', credits: 4.0, marks: 71, grade: 'A-' }
    ],
    mba: [
      { id: 1, title: 'Organizational Behavior (MBA 501)', credits: 3.0, marks: 78, grade: 'A' },
      { id: 2, title: 'Managerial Economics (MBA 502)', credits: 3.0, marks: 82, grade: 'A+' },
      { id: 3, title: 'Corporate Finance (MBA 503)', credits: 3.0, marks: 75, grade: 'A' },
      { id: 4, title: 'Marketing Management (MBA 504)', credits: 3.0, marks: 86, grade: 'A+' }
    ],
    hsc: [
      { id: 1, title: 'Bangla 1st & 2nd Paper (HSC 1801)', credits: 3.0, marks: 76, grade: 'A' },
      { id: 2, title: 'English 1st & 2nd Paper (HSC 1802)', credits: 3.0, marks: 68, grade: 'B+' },
      { id: 3, title: 'Information & Comm. Technology (HSC 1803)', credits: 3.0, marks: 88, grade: 'A+' },
      { id: 4, title: 'Civics & Good Governance (HSC 1804)', credits: 3.0, marks: 72, grade: 'A-' }
    ]
  };

  if (presets[presetKey]) {
    state.courses = JSON.parse(JSON.stringify(presets[presetKey]));
    renderCourses();
    showToast((state.lang === 'en') ? `Loaded ${presetKey.toUpperCase()} template` : `${presetKey.toUpperCase()} টেমপ্লেট লোড হয়েছে`);
  }
}

// Multi-Semester Cumulative CGPA Section
function addSemesterRow() {
  const nextId = (state.semesters.length > 0 ? Math.max(...state.semesters.map(s => s.id)) : 0) + 1;
  state.semesters.push({
    id: nextId,
    name: 'Semester ' + nextId,
    credits: 15,
    gpa: 3.50
  });
  renderSemesters();
}

function removeSemesterRow(id) {
  if (state.semesters.length <= 1) {
    showToast(state.lang === 'en' ? 'At least one semester is required' : 'নূন্যতম একটি সেমিস্টার আবশ্যক');
    return;
  }
  state.semesters = state.semesters.filter(s => s.id !== id);
  renderSemesters();
}

function updateSemesterField(id, field, val) {
  const sem = state.semesters.find(s => s.id === id);
  if (!sem) return;
  if (field === 'name') sem.name = val;
  if (field === 'credits') sem.credits = Math.max(1, Number(val) || 0);
  if (field === 'gpa') sem.gpa = Math.max(0, Math.min(4.00, Number(val) || 0));
  recalculateCumulative();
}

function recalculateCumulative() {
  let totalCredits = 0;
  let totalQuality = 0;

  state.semesters.forEach(s => {
    const cr = Number(s.credits) || 0;
    const gpa = Number(s.gpa) || 0;
    totalCredits += cr;
    totalQuality += cr * gpa;
  });

  const cgpa = totalCredits > 0 ? (totalQuality / totalCredits) : 0;
  const cumCgpaEl = document.getElementById('cum-stat-cgpa');
  const cumCreditsEl = document.getElementById('cum-stat-credits');
  const cumClassEl = document.getElementById('cum-stat-class');

  if (cumCgpaEl) cumCgpaEl.innerText = cgpa.toFixed(2);
  if (cumCreditsEl) cumCreditsEl.innerText = totalCredits.toFixed(1);

  let classText = '';
  if (totalCredits === 0) classText = 'Pending';
  else if (cgpa >= 3.75) classText = (state.lang === 'en') ? 'First Class with Distinction' : 'ডিস্টিংকশন সহ প্রথম শ্রেণি';
  else if (cgpa >= 3.00) classText = (state.lang === 'en') ? 'First Class / Division' : 'প্রথম শ্রেণি / বিভাগ';
  else if (cgpa >= 2.25) classText = (state.lang === 'en') ? 'Second Class / Division' : 'দ্বিতীয় শ্রেণি / বিভাগ';
  else if (cgpa >= 2.00) classText = (state.lang === 'en') ? 'Pass Division' : 'পাস বিভাগ';
  else classText = (state.lang === 'en') ? 'Probation' : 'প্রবেশন';

  if (cumClassEl) cumClassEl.innerText = classText;

  // Update progress tab bar
  updateDegreeProgressBar(totalCredits, cgpa);
}

function renderSemesters() {
  const tbody = document.getElementById('semester-rows');
  if (!tbody) return;

  tbody.innerHTML = state.semesters.map(s => `
    <tr class="hover:bg-slate-50 transition-colors">
      <td class="py-2.5 px-3">
        <input type="text" value="${s.name}" oninput="updateSemesterField(${s.id}, 'name', this.value)" class="w-full px-2 py-1 rounded border border-slate-200 text-xs font-semibold text-slate-800 bg-transparent"/>
      </td>
      <td class="py-2.5 px-3">
        <input type="number" min="1" max="30" value="${s.credits}" oninput="updateSemesterField(${s.id}, 'credits', this.value)" class="w-24 px-2 py-1 rounded border border-slate-300 font-mono text-xs focus:ring-1 focus:ring-bou-green"/>
      </td>
      <td class="py-2.5 px-3">
        <input type="number" step="0.01" min="0" max="4" value="${s.gpa}" oninput="updateSemesterField(${s.id}, 'gpa', this.value)" class="w-24 px-2 py-1 rounded border border-slate-300 font-mono font-bold text-xs focus:ring-1 focus:ring-bou-green"/>
      </td>
      <td class="py-2.5 px-3 font-mono text-xs font-bold text-slate-600">
        ${(s.credits * s.gpa).toFixed(2)}
      </td>
      <td class="py-2.5 px-2 text-center">
        <button onclick="removeSemesterRow(${s.id})" class="p-1 rounded text-slate-400 hover:text-bou-crimson hover:bg-rose-50 transition-colors">
          <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/></svg>
        </button>
      </td>
    </tr>
  `).join('');

  recalculateCumulative();
}

// Section 11.1: Required GPA Target Planner
function calculateTargetPlan() {
  const currentCGPA = parseFloat(document.getElementById('plan-current-cgpa').value) || 0;
  const completedCredits = parseFloat(document.getElementById('plan-completed-credits').value) || 0;
  const remainingCredits = parseFloat(document.getElementById('plan-remaining-credits').value) || 0;
  const targetCGPA = parseFloat(document.getElementById('plan-target-cgpa').value) || 0;

  const resultBox = document.getElementById('plan-result-box');
  const resultGpaEl = document.getElementById('plan-result-gpa');
  const resultMsgEl = document.getElementById('plan-result-msg');

  if (remainingCredits <= 0) {
    showToast(state.lang === 'en' ? 'Remaining credits must be > 0' : 'অবশিষ্ট ক্রেডিট অবশ্যই ০-এর বেশি হতে হবে');
    return;
  }

  const totalCredits = completedCredits + remainingCredits;
  const neededPoints = (targetCGPA * totalCredits) - (currentCGPA * completedCredits);
  const reqGPA = neededPoints / remainingCredits;

  resultBox.classList.remove('hidden');

  if (reqGPA > 4.00) {
    resultGpaEl.innerText = reqGPA.toFixed(2);
    resultGpaEl.className = 'text-2xl font-extrabold font-mono text-bou-crimson';
    resultMsgEl.innerText = (state.lang === 'en')
      ? `Mathematically Unattainable: Target requires a GPA of ${reqGPA.toFixed(2)} on remaining credits, which exceeds the BOU maximum scale of 4.00.`
      : `অর্জনের অতীত: কাঙ্ক্ষিত সিজিপিএ অর্জন করতে বাকি ক্রেডিটে ${reqGPA.toFixed(2)} জিপিএ প্রয়োজন, যা বাউবির সর্বোচ্চ ৪.০০ স্কেলের বেশি।`;
  } else if (reqGPA <= 2.00) {
    resultGpaEl.innerText = '2.00 (Pass)';
    resultGpaEl.className = 'text-2xl font-extrabold font-mono text-emerald-700';
    resultMsgEl.innerText = (state.lang === 'en')
      ? `Comfortably Achievable: Maintaining minimum pass (C- / 2.00) on remaining credits is sufficient to meet your target.`
      : `সহজেই অর্জনযোগ্য: বাকি সকল বিষয়ে নূন্যতম পাস (C- / ২.০০) করলেই কাঙ্ক্ষিত সিজিপিএ অর্জিত হবে।`;
  } else {
    resultGpaEl.innerText = reqGPA.toFixed(2);
    resultGpaEl.className = 'text-2xl font-extrabold font-mono text-bou-green';
    resultMsgEl.innerText = (state.lang === 'en')
      ? `Feasible: You must maintain an average GPA of at least ${reqGPA.toFixed(2)} across the remaining ${remainingCredits} credits.`
      : `সম্ভব: বাকি ${remainingCredits} ক্রেডিটে গড়ে নূন্যতম ${reqGPA.toFixed(2)} জিপিএ ধরে রাখতে হবে।`;
  }
}

// Degree Progress Tracker
function updateDegreeProgressBar(earnedCredits, cgpa) {
  const reqCredits = state.programTotalCredits || 120;
  const pct = Math.min(100, Math.round((earnedCredits / reqCredits) * 100));
  const remaining = Math.max(0, reqCredits - earnedCredits);

  const barEl = document.getElementById('progress-bar-fill');
  const pctEl = document.getElementById('progress-pct-text');
  const earnedText = document.getElementById('progress-earned-text');
  const remText = document.getElementById('progress-remaining-text');

  if (barEl) barEl.style.width = pct + '%';
  if (pctEl) pctEl.innerText = pct + '%';
  if (earnedText) earnedText.innerText = earnedCredits.toFixed(1) + ' / ' + reqCredits + ' Credits';
  if (remText) remText.innerText = remaining.toFixed(1) + ' Credits Remaining';
}

function updateProgramRequirement(totalCr) {
  state.programTotalCredits = Number(totalCr) || 120;
  recalculateCumulative();
}

// Profile Local Persistence
function loadSavedProfile() {
  try {
    const saved = localStorage.getItem('bou_student_profile');
    if (saved) state.profile = JSON.parse(saved);
  } catch (e) {
    console.error(e);
  }
  updateProfileDOM();
}

function updateProfileDOM() {
  const p = state.profile;
  const hId = document.getElementById('home-card-id');
  const hProg = document.getElementById('home-card-program');
  const hRc = document.getElementById('home-card-rc');
  if (hId) hId.innerText = p.studentId || '21-0-11-020-045';
  if (hProg) hProg.innerText = p.program || 'Bachelor of Business Administration (BBA)';
  if (hRc) hRc.innerText = p.rc || 'Dhaka Regional Center (RC 11)';

  const cId = document.getElementById('card-student-id');
  const cName = document.getElementById('card-student-name');
  const cProg = document.getElementById('card-program');
  const cSess = document.getElementById('card-session');
  const cRc = document.getElementById('card-rc');

  if (cId) cId.innerText = p.studentId || '21-0-11-020-045';
  if (cName) cName.innerText = p.name || 'Student Name';
  if (cProg) cProg.innerText = p.program || 'Program';
  if (cSess) cSess.innerText = p.session || '2021-2022';
  if (cRc) cRc.innerText = p.rc || 'Regional Center';

  const fId = document.getElementById('input-id');
  const fName = document.getElementById('input-name');
  const fProg = document.getElementById('input-program');
  const fSess = document.getElementById('input-session');
  const fRc = document.getElementById('input-rc');
  const fSc = document.getElementById('input-study-center');

  if (fId) fId.value = p.studentId || '';
  if (fName) fName.value = p.name || '';
  if (fProg) fProg.value = p.program || 'Bachelor of Business Administration (BBA)';
  if (fSess) fSess.value = p.session || '';
  if (fRc) fRc.value = p.rc || 'Dhaka Regional Center (RC 11)';
  if (fSc) fSc.value = p.studyCenter || '';
}

function saveProfile(e) {
  if (e) e.preventDefault();
  state.profile = {
    studentId: (document.getElementById('input-id').value || '').trim(),
    name: (document.getElementById('input-name').value || '').trim(),
    program: document.getElementById('input-program').value,
    session: (document.getElementById('input-session').value || '').trim(),
    rc: document.getElementById('input-rc').value,
    studyCenter: (document.getElementById('input-study-center').value || '').trim()
  };

  try {
    localStorage.setItem('bou_student_profile', JSON.stringify(state.profile));
  } catch (err) {
    console.error(err);
  }

  updateProfileDOM();
  showToast(state.lang === 'en' ? 'Profile saved to your device' : 'প্রোফাইল আপনার ডিভাইসে সংরক্ষিত হয়েছে');
}

function clearLocalData() {
  if (confirm(state.lang === 'en' ? 'Clear all locally saved profile data from this browser?' : 'ব্রাউজার থেকে সংরক্ষিত প্রোফাইল তথ্য মুছে ফেলতে চান?')) {
    localStorage.removeItem('bou_student_profile');
    state.profile = {
      studentId: '',
      name: '',
      program: 'Bachelor of Business Administration (BBA)',
      session: '',
      rc: 'Dhaka Regional Center (RC 11)',
      studyCenter: ''
    };
    updateProfileDOM();
    showToast(state.lang === 'en' ? 'Local profile cleared' : 'তথ্য মুছে ফেলা হয়েছে');
  }
}

function copyStudentId() {
  const id = state.profile.studentId || '21-0-11-020-045';
  copyToClipboard(id, (state.lang === 'en') ? `Student ID ${id} copied!` : `আইডি ${id} কপি হয়েছে!`);
}

function copyToClipboard(text, msg) {
  if (navigator.clipboard && navigator.clipboard.writeText) {
    navigator.clipboard.writeText(text).then(() => showToast(msg)).catch(() => fallbackCopy(text, msg));
  } else {
    fallbackCopy(text, msg);
  }
}

function fallbackCopy(text, msg) {
  const ta = document.createElement('textarea');
  ta.value = text;
  ta.style.position = 'fixed';
  ta.style.opacity = '0';
  document.body.appendChild(ta);
  ta.focus();
  ta.select();
  try {
    document.execCommand('copy');
    showToast(msg || 'Copied to clipboard');
  } catch (e) {
    showToast('Manual copy: ' + text);
  }
  document.body.removeChild(ta);
}

function showToast(message) {
  const container = document.getElementById('toast-container');
  if (!container) return;

  const toast = document.createElement('div');
  toast.className = 'pointer-events-auto flex items-center space-x-2 bg-bou-navy text-white text-xs font-semibold px-4 py-3 rounded-xl shadow-xl border border-slate-700 animate-bounce duration-300';
  toast.innerHTML = `
    <svg class="w-4 h-4 text-bou-gold shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" d="M5 13l4 4L19 7"/></svg>
    <span>${message}</span>
  `;
  container.appendChild(toast);

  setTimeout(() => {
    toast.style.opacity = '0';
    toast.style.transform = 'translateY(10px)';
    toast.style.transition = 'all 0.3s ease';
    setTimeout(() => toast.remove(), 300);
  }, 2800);
}

// Search filters
function filterBentoGrid() {
  const q = (document.getElementById('portal-search').value || '').trim().toLowerCase();
  const items = document.querySelectorAll('.portal-item');
  let count = 0;
  items.forEach(item => {
    const text = (item.getAttribute('data-search') || '') + ' ' + item.innerText.toLowerCase();
    if (!q || text.includes(q)) {
      item.style.display = '';
      count++;
    } else {
      item.style.display = 'none';
    }
  });
  const countEl = document.getElementById('search-count');
  if (q) {
    countEl.classList.remove('hidden');
    countEl.innerText = count + ' found';
  } else {
    countEl.classList.add('hidden');
  }
}

function filterRegionalCenters() {
  const q = (document.getElementById('rc-search-input').value || '').trim().toLowerCase();
  const items = document.querySelectorAll('.rc-card-item');
  items.forEach(card => {
    const txt = card.innerText.toLowerCase();
    if (!q || txt.includes(q)) card.style.display = '';
    else card.style.display = 'none';
  });
}

// BST Clock & Office Hours
function updateClock() {
  const now = new Date();
  const utc = now.getTime() + (now.getTimezoneOffset() * 60000);
  const bst = new Date(utc + (3600000 * 6)); // UTC + 6
  const hours = bst.getHours();
  const minutes = bst.getMinutes().toString().padStart(2, '0');
  const seconds = bst.getSeconds().toString().padStart(2, '0');
  const ampm = hours >= 12 ? 'PM' : 'AM';
  const displayHours = (hours % 12 || 12).toString().padStart(2, '0');
  const day = bst.getDay(); // 0 = Sun, 4 = Thu

  const clockEl = document.getElementById('clock-display');
  if (clockEl) clockEl.innerText = `BST: ${displayHours}:${minutes}:${seconds} ${ampm}`;

  const isOfficeOpen = (day >= 0 && day <= 4) && (hours >= 9 && hours < 17);
  const statusEl = document.getElementById('office-status');
  if (statusEl) {
    if (isOfficeOpen) {
      statusEl.innerText = (state.lang === 'en') ? 'Helplines Open (Office Hours)' : 'হেল্পলাইন খোলা রয়েছে';
      statusEl.className = 'hidden sm:inline-flex items-center px-2.5 py-0.5 rounded font-bold text-[11px] bg-emerald-900/60 text-emerald-300 border border-emerald-700/50';
    } else {
      statusEl.innerText = (state.lang === 'en') ? 'Helplines Closed (Opens 9 AM Sun–Thu)' : 'হেল্পলাইন বন্ধ (রবি-বৃহস্পতি সকাল ৯টায় খুলবে)';
      statusEl.className = 'hidden sm:inline-flex items-center px-2.5 py-0.5 rounded font-bold text-[11px] bg-amber-900/60 text-amber-300 border border-amber-700/50';
    }
  }
}

document.addEventListener('DOMContentLoaded', () => {
  loadSavedProfile();
  renderCourses();
  renderSemesters();
  updateClock();
  setInterval(updateClock, 1000);
});
