// Bangladesh Open University (BOU) Official Academic Calculation Engine
// Strictly follows BOU 4.00 standard grading scale regulations

const BOU_GRADES = [
  { min: 80, max: 100, grade: 'A+', point: 4.00, remarkEn: 'Outstanding', remarkBn: 'অসাধারণ' },
  { min: 75, max: 79.99, grade: 'A', point: 3.75, remarkEn: 'Excellent', remarkBn: 'চমৎকার' },
  { min: 70, max: 74.99, grade: 'A-', point: 3.50, remarkEn: 'Very Good', remarkBn: 'অনেক ভালো' },
  { min: 65, max: 69.99, grade: 'B+', point: 3.25, remarkEn: 'Good', remarkBn: 'ভালো' },
  { min: 60, max: 64.99, grade: 'B', point: 3.00, remarkEn: 'Satisfactory', remarkBn: 'সন্তোষজনক' },
  { min: 55, max: 59.99, grade: 'B-', point: 2.75, remarkEn: 'Above Average', remarkBn: 'গড়ের উপরে' },
  { min: 50, max: 54.99, grade: 'C+', point: 2.50, remarkEn: 'Average', remarkBn: 'গড়' },
  { min: 45, max: 49.99, grade: 'C', point: 2.25, remarkEn: 'Below Average', remarkBn: 'গড়ের নিচে' },
  { min: 40, max: 44.99, grade: 'C-', point: 2.00, remarkEn: 'Pass', remarkBn: 'উত্তীর্ণ (পাস)' },
  { min: 0, max: 39.99, grade: 'F', point: 0.00, remarkEn: 'Fail', remarkBn: 'অনুত্তীর্ণ (ফেল)' }
];

/**
 * Converts numeric percentage mark to official BOU grade and grade point.
 * @param {number|string} marks
 * @returns {{ grade: string, point: number, remarkEn: string, remarkBn: string }}
 */
function marksToGrade(marks) {
  const num = Number(marks);
  if (isNaN(num) || num < 0) {
    return { grade: 'F', point: 0.00, remarkEn: 'Fail (Invalid/Negative)', remarkBn: 'অনুত্তীর্ণ' };
  }
  if (num > 100) {
    // Cap at 100
    return { grade: 'A+', point: 4.00, remarkEn: 'Outstanding (Exceeds 100)', remarkBn: 'অসাধারণ' };
  }
  for (const item of BOU_GRADES) {
    if (num >= item.min) {
      return {
        grade: item.grade,
        point: item.point,
        remarkEn: item.remarkEn,
        remarkBn: item.remarkBn
      };
    }
  }
  return { grade: 'F', point: 0.00, remarkEn: 'Fail', remarkBn: 'অনুত্তীর্ণ' };
}

/**
 * Converts letter grade string to grade object.
 * @param {string} gradeStr
 */
function gradeNameToGrade(gradeStr) {
  const clean = String(gradeStr || '').trim().toUpperCase();
  const found = BOU_GRADES.find(g => g.grade === clean);
  if (found) {
    return {
      grade: found.grade,
      point: found.point,
      remarkEn: found.remarkEn,
      remarkBn: found.remarkBn
    };
  }
  return { grade: clean || 'F', point: 0.00, remarkEn: 'Unknown/Fail', remarkBn: 'অজানা/ফেল' };
}

/**
 * Calculates Semester GPA and credit metrics.
 * @param {Array<{ credits: number, marks?: number, grade?: string }>} courses
 */
function calculateSemesterGPA(courses) {
  let totalAttemptedCredits = 0;
  let totalEarnedCredits = 0;
  let totalQualityPoints = 0;
  let failedCount = 0;

  for (const course of courses) {
    const credits = Math.max(0, Number(course.credits) || 0);
    if (credits === 0) continue;

    let gradeObj;
    if (course.marks !== undefined && course.marks !== null && course.marks !== '') {
      gradeObj = marksToGrade(course.marks);
    } else {
      gradeObj = gradeNameToGrade(course.grade);
    }

    totalAttemptedCredits += credits;
    if (gradeObj.grade !== 'F' && gradeObj.point >= 2.00) {
      totalEarnedCredits += credits;
    } else {
      failedCount++;
    }

    totalQualityPoints += credits * gradeObj.point;
  }

  const gpa = totalAttemptedCredits > 0 ? (totalQualityPoints / totalAttemptedCredits) : 0;

  return {
    gpa: Number(gpa.toFixed(2)),
    attemptedCredits: Number(totalAttemptedCredits.toFixed(1)),
    earnedCredits: Number(totalEarnedCredits.toFixed(1)),
    qualityPoints: Number(totalQualityPoints.toFixed(2)),
    failedCount,
    classification: getDegreeClassification(gpa)
  };
}

/**
 * Calculates Cumulative CGPA across multiple semesters.
 * @param {Array<{ semesterName?: string, credits: number, gpa: number }>} semesters
 */
function calculateCumulativeCGPA(semesters) {
  let totalCredits = 0;
  let totalPoints = 0;

  for (const sem of semesters) {
    const cr = Math.max(0, Number(sem.credits) || 0);
    const gpa = Math.max(0, Math.min(4.00, Number(sem.gpa) || 0));
    totalCredits += cr;
    totalPoints += cr * gpa;
  }

  const cgpa = totalCredits > 0 ? (totalPoints / totalCredits) : 0;
  return {
    cgpa: Number(cgpa.toFixed(2)),
    totalCredits: Number(totalCredits.toFixed(1)),
    classification: getDegreeClassification(cgpa)
  };
}

/**
 * Calculates required GPA on remaining credits to reach a target CGPA.
 * Formula: Required = (Target * TotalCredits - CurrentCGPA * CompletedCredits) / RemainingCredits
 */
function calculateRequiredGPA(currentCGPA, completedCredits, remainingCredits, targetCGPA) {
  const current = Math.max(0, Math.min(4.00, Number(currentCGPA) || 0));
  const completed = Math.max(0, Number(completedCredits) || 0);
  const remaining = Math.max(0, Number(remainingCredits) || 0);
  const target = Math.max(0, Math.min(4.00, Number(targetCGPA) || 0));

  if (remaining <= 0) {
    return {
      achievable: false,
      requiredGPA: 0,
      messageEn: 'Remaining credits must be greater than 0.',
      messageBn: 'অবশিষ্ট ক্রেডিট অবশ্যই ০-এর বেশি হতে হবে।'
    };
  }

  const totalCredits = completed + remaining;
  const targetPoints = target * totalCredits;
  const currentPoints = current * completed;
  const neededPoints = targetPoints - currentPoints;
  const reqGPA = neededPoints / remaining;

  if (reqGPA > 4.00) {
    return {
      achievable: false,
      requiredGPA: Number(reqGPA.toFixed(2)),
      messageEn: `Mathematically unattainable (Requires ${reqGPA.toFixed(2)} which exceeds 4.00 maximum).`,
      messageBn: `অর্জনের বাইরে (প্রয়োজন ${reqGPA.toFixed(2)}, যা সর্বোচ্চ ৪.০০-এর বেশি)।`
    };
  }

  if (reqGPA < 2.00) {
    return {
      achievable: true,
      requiredGPA: 2.00,
      messageEn: 'Easily achievable. Passing all remaining courses with minimum pass (2.00 / C-) is sufficient.',
      messageBn: 'সহজেই অর্জনযোগ্য। অবশিষ্ট কোর্সে নূন্যতম পাস (২.০০ / C-) করলেই লক্ষ্য পূরণ হবে।'
    };
  }

  return {
    achievable: true,
    requiredGPA: Number(reqGPA.toFixed(2)),
    messageEn: `Requires maintaining an average GPA of ${reqGPA.toFixed(2)} in all remaining ${remaining} credits.`,
    messageBn: `অবশিষ্ট ${remaining} ক্রেডিটে গড়ে নূন্যতম ${reqGPA.toFixed(2)} জিপিএ পেতে হবে।`
  };
}

/**
 * Returns Official BOU Degree Classification.
 * @param {number} cgpa
 */
function getDegreeClassification(cgpa) {
  const score = Number(cgpa);
  if (isNaN(score) || score <= 0) {
    return {
      nameEn: 'Awaiting Results',
      nameBn: 'ফলাফলের অপেক্ষায়',
      code: 'pending',
      badgeClass: 'bg-slate-100 text-slate-700'
    };
  }
  if (score >= 3.75) {
    return {
      nameEn: 'First Class with Distinction',
      nameBn: 'ডিস্টিংকশন সহ প্রথম শ্রেণি',
      code: 'first_distinction',
      badgeClass: 'bg-emerald-100 text-emerald-800'
    };
  }
  if (score >= 3.00) {
    return {
      nameEn: 'First Class / First Division',
      nameBn: 'প্রথম শ্রেণি / বিভাগ',
      code: 'first_class',
      badgeClass: 'bg-blue-100 text-blue-800'
    };
  }
  if (score >= 2.25) {
    return {
      nameEn: 'Second Class / Second Division',
      nameBn: 'দ্বিতীয় শ্রেণি / বিভাগ',
      code: 'second_class',
      badgeClass: 'bg-amber-100 text-amber-900'
    };
  }
  if (score >= 2.00) {
    return {
      nameEn: 'Pass Division',
      nameBn: 'পাস বিভাগ',
      code: 'pass',
      badgeClass: 'bg-slate-100 text-slate-800'
    };
  }
  return {
    nameEn: 'Unsatisfactory / Academic Probation',
    nameBn: 'অসন্তোষজনক / প্রবেশন',
    code: 'probation',
    badgeClass: 'bg-rose-100 text-rose-800'
  };
}

if (typeof module !== 'undefined' && module.exports) {
  module.exports = {
    BOU_GRADES,
    marksToGrade,
    gradeNameToGrade,
    calculateSemesterGPA,
    calculateCumulativeCGPA,
    calculateRequiredGPA,
    getDegreeClassification
  };
}
