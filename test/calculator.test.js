const assert = require('assert');
const {
  BOU_GRADES,
  marksToGrade,
  gradeNameToGrade,
  calculateSemesterGPA,
  calculateCumulativeCGPA,
  calculateRequiredGPA,
  getDegreeClassification
} = require('../src/calculator');

console.log('Running BOU Academic Calculation Engine Tests (Section 21 of PLAN.md)...');

// 1. Exact boundary tests
assert.strictEqual(marksToGrade(80).grade, 'A+');
assert.strictEqual(marksToGrade(80).point, 4.00);

assert.strictEqual(marksToGrade(79).grade, 'A');
assert.strictEqual(marksToGrade(79).point, 3.75);

assert.strictEqual(marksToGrade(75).grade, 'A');
assert.strictEqual(marksToGrade(75).point, 3.75);

assert.strictEqual(marksToGrade(74).grade, 'A-');
assert.strictEqual(marksToGrade(74).point, 3.50);

assert.strictEqual(marksToGrade(70).grade, 'A-');
assert.strictEqual(marksToGrade(70).point, 3.50);

assert.strictEqual(marksToGrade(69).grade, 'B+');
assert.strictEqual(marksToGrade(69).point, 3.25);

assert.strictEqual(marksToGrade(65).grade, 'B+');
assert.strictEqual(marksToGrade(65).point, 3.25);

assert.strictEqual(marksToGrade(64).grade, 'B');
assert.strictEqual(marksToGrade(64).point, 3.00);

assert.strictEqual(marksToGrade(60).grade, 'B');
assert.strictEqual(marksToGrade(60).point, 3.00);

assert.strictEqual(marksToGrade(59).grade, 'B-');
assert.strictEqual(marksToGrade(59).point, 2.75);

assert.strictEqual(marksToGrade(55).grade, 'B-');
assert.strictEqual(marksToGrade(55).point, 2.75);

assert.strictEqual(marksToGrade(54).grade, 'C+');
assert.strictEqual(marksToGrade(54).point, 2.50);

assert.strictEqual(marksToGrade(50).grade, 'C+');
assert.strictEqual(marksToGrade(50).point, 2.50);

assert.strictEqual(marksToGrade(49).grade, 'C');
assert.strictEqual(marksToGrade(49).point, 2.25);

assert.strictEqual(marksToGrade(45).grade, 'C');
assert.strictEqual(marksToGrade(45).point, 2.25);

assert.strictEqual(marksToGrade(44).grade, 'C-');
assert.strictEqual(marksToGrade(44).point, 2.00);

assert.strictEqual(marksToGrade(40).grade, 'C-');
assert.strictEqual(marksToGrade(40).point, 2.00);

assert.strictEqual(marksToGrade(39).grade, 'F');
assert.strictEqual(marksToGrade(39).point, 0.00);

assert.strictEqual(marksToGrade(0).grade, 'F');
assert.strictEqual(marksToGrade(-5).grade, 'F');
assert.strictEqual(marksToGrade(105).grade, 'A+');

console.log('✓ All 20 Grade Boundary tests passed.');

// 2. Semester GPA with mixed inputs, fail courses, attempted vs earned
const coursesSample = [
  { credits: 3.0, marks: 82 }, // A+ (4.00) -> 12 points
  { credits: 3.0, marks: 77 }, // A (3.75) -> 11.25 points
  { credits: 3.0, marks: 72 }, // A- (3.50) -> 10.5 points
  { credits: 3.0, marks: 68 }, // B+ (3.25) -> 9.75 points
  { credits: 3.0, marks: 35 }  // F (0.00) -> 0 points (Failed)
];

const semResult = calculateSemesterGPA(coursesSample);
assert.strictEqual(semResult.attemptedCredits, 15.0);
assert.strictEqual(semResult.earnedCredits, 12.0);
assert.strictEqual(semResult.failedCount, 1);
// (12 + 11.25 + 10.5 + 9.75 + 0) / 15 = 43.5 / 15 = 2.90
assert.strictEqual(semResult.gpa, 2.90);
assert.strictEqual(semResult.classification.code, 'second_class');

console.log('✓ Semester GPA & Earned vs Attempted credits calculation passed.');

// 3. Multi-semester Cumulative CGPA
const semList = [
  { credits: 15, gpa: 3.50 }, // 52.5 points
  { credits: 18, gpa: 3.80 }, // 68.4 points
  { credits: 15, gpa: 3.20 }  // 48.0 points
];
// (52.5 + 68.4 + 48.0) / 48 = 168.9 / 48 = 3.51875 -> 3.52
const cumResult = calculateCumulativeCGPA(semList);
assert.strictEqual(cumResult.totalCredits, 48.0);
assert.strictEqual(cumResult.cgpa, 3.52);
assert.strictEqual(cumResult.classification.code, 'first_class');

console.log('✓ Cumulative Multi-Semester CGPA calculation passed.');

// 4. Required GPA Target Planner
// Current: 3.20 on 60 credits. Target: 3.50. Remaining: 60 credits.
// Needed: (3.50 * 120 - 3.20 * 60) / 60 = (420 - 192) / 60 = 228 / 60 = 3.80
const reqPlan = calculateRequiredGPA(3.20, 60, 60, 3.50);
assert.strictEqual(reqPlan.achievable, true);
assert.strictEqual(reqPlan.requiredGPA, 3.80);

// Impossible target
// Current: 2.50 on 100 credits. Target: 3.80. Remaining: 20 credits.
// Needed: (3.80 * 120 - 2.50 * 100) / 20 = (456 - 250) / 20 = 206 / 20 = 10.30 -> Unattainable
const impPlan = calculateRequiredGPA(2.50, 100, 20, 3.80);
assert.strictEqual(impPlan.achievable, false);

console.log('✓ Required GPA calculation & target feasibility tests passed.');
console.log('ALL UNIT TESTS PASSED SUCCESSFULLY! 🎉');
