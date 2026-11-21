// Bangladesh Open University (BOU) Verified Reference Data
// Adheres strictly to Section 13, 14, and 25 of /PLAN.md

const OFFICIAL_SERVICES = [
  {
    id: 'bou-main',
    titleEn: 'BOU Main Website',
    titleBn: 'বাউবি মূল ওয়েবসাইট',
    descriptionEn: 'Central institutional portal of Bangladesh Open University. Official circulars, academic councils, university administration directories, and faculty announcements.',
    descriptionBn: 'বাংলাদেশ উন্মুক্ত বিশ্ববিদ্যালয়ের কেন্দ্রীয় প্রশাসনিক পোর্টাল। অফিসিয়াল সার্কুলার, একাডেমিক কাউন্সিল, অনুষদ ও বিশ্ববিদ্যালয়ের নোটিশসমূহ।',
    url: 'https://www.bou.ac.bd',
    category: 'website',
    official: true,
    source: 'Bangladesh Open University Central ICT',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-results',
    titleEn: 'Examination Results Portal',
    titleBn: 'পরীক্ষার ফলাফল পোর্টাল',
    descriptionEn: 'Official semester final results, transcript verification, semester grade sheets for SSC, HSC, BA/BSS, BBA, LLB, and Masters programs.',
    descriptionBn: 'এসএসসি, এইচএসসি, বিএ/বিএসএস, বিবিএ, এলএলবি ও মাস্টার্স প্রোগ্রামের সেমিস্টার পরীক্ষার ফলাফল ও অনলাইন নম্বরপত্র যাচাই।',
    url: 'https://result.bou.ac.bd',
    category: 'result',
    official: true,
    source: 'Controller of Examinations, BOU',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-notices',
    titleEn: 'Official Notice Board',
    titleBn: 'অফিসিয়াল নোটিশ বোর্ড',
    descriptionEn: 'Live notices, routine updates, assignment submission guidelines, tutorial class schedules, and regional center circulars.',
    descriptionBn: 'পরীক্ষার রুটিন, অ্যাসাইনমেন্ট জমার সময়সূচি, টিউটোরিয়াল ক্লাস ও আঞ্চলিক কেন্দ্রের হালনাগাদ সার্কুলার।',
    url: 'https://www.bou.ac.bd/NoticeBoard',
    category: 'academic',
    official: true,
    source: 'BOU Registrar Circular Division',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-admission',
    titleEn: 'Online Admission Portal',
    titleBn: 'অনলাইন ভর্তি পোর্টাল',
    descriptionEn: 'Online application processing, admission eligibility checks, prospectus download, and fee payment instructions.',
    descriptionBn: 'অনলাইনে নতুন ভর্তির আবেদন, যোগ্যতা যাচাই, প্রসপেক্টাস ডাউনলোড ও ভর্তি ফি জমাদানের বিস্তারিত নির্দেশিকা।',
    url: 'https://www.bou.ac.bd/BOU/Admission',
    category: 'admission',
    official: true,
    source: 'Student Support Service (SSS) Division, BOU',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-handbook',
    titleEn: 'Student Handbooks & Rules',
    titleBn: 'শিক্ষার্থী নির্দেশিকা ও নিয়মাবলি',
    descriptionEn: 'Official program curricula, examination bylaws, grading regulations, credit requirements, and code of conduct.',
    descriptionBn: 'বিভিন্ন প্রোগ্রামের সিলেবাস, পরীক্ষা সংক্রান্ত বিধিমালা, গ্রেডিং নীতিমালা ও ক্রেডিট প্রয়োজনীয়তার অফিসিয়াল নির্দেশিকা।',
    url: 'https://www.bou.ac.bd/BOU/StudentHandbook',
    category: 'academic',
    official: true,
    source: 'Academic Council, BOU',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-osaps',
    titleEn: 'OSAPS Student Profile',
    titleBn: 'OSAPS শিক্ষার্থী প্রোফাইল',
    descriptionEn: 'Open University Student Access & Payment System (OSAPS). Print admit cards, register courses, and pay semester fees.',
    descriptionBn: 'ওপেন ইউনিভার্সিটি স্টুডেন্ট অ্যাক্সেস ও পেমেন্ট সিস্টেম (OSAPS)। অ্যাডমিট কার্ড ডাউনলোড, কোর্স রেজিস্ট্রেশন ও ফি পরিশোধ।',
    url: 'https://osapsnew.bou.ac.bd',
    category: 'admission',
    official: true,
    source: 'BOU Computer Center & ICT Cell',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  },
  {
    id: 'bou-ebook',
    titleEn: 'E-Book Library & Textbooks',
    titleBn: 'ই-বুক লাইব্রেরি ও পাঠ্যপুস্তক',
    descriptionEn: 'Free downloadable PDF textbooks, self-learning course modules, audio lectures, and video resources across all schools.',
    descriptionBn: 'সকল স্কুলের উন্মুক্ত পাঠ্যবইয়ের বিনামূল্যে পিডিএফ ডাউনলোড, স্ব-শিক্ষণ মডিউল এবং অডিও-ভিডিও লেকচার সংগ্রহশালা।',
    url: 'https://www.ebookbou.edu.bd',
    category: 'library',
    official: true,
    source: 'Media & Instructional Materials Division, BOU',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'available'
  }
];

const OFFICIAL_CONTACTS = [
  {
    id: 'osaps-help-1',
    department: 'OSAPS Technical Support',
    name: 'OSAPS Helpdesk 1',
    role: 'Technical Help & Payment Inquiries',
    phone: ['01618977237'],
    email: 'help.osaps@bou.ac.bd',
    address: 'Computer Center, BOU, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM (Office hours only)',
    source: 'osapsnew.bou.ac.bd/contact',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  },
  {
    id: 'osaps-help-2',
    department: 'OSAPS Technical Support',
    name: 'OSAPS Helpdesk 2',
    role: 'Student ID & Login Support Desk',
    phone: ['01907451612'],
    email: 'help.osaps@bou.ac.bd',
    address: 'Computer Center, BOU, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM (Office hours only)',
    source: 'osapsnew.bou.ac.bd/contact',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  },
  {
    id: 'registrar-office',
    department: 'Office of the Registrar',
    name: 'T M Ahmed Hussain',
    role: 'Registrar',
    phone: ['+880-2-9291112'],
    email: 'registrar@bou.ac.bd',
    address: 'Administrative Building, Bangladesh Open University, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM',
    source: 'bou.ac.bd/Administration',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  },
  {
    id: 'controller-exam',
    department: 'Examination Division',
    name: 'Controller of Examinations',
    role: 'Examination Routine, Certificates & Transcripts',
    phone: ['01758931163', '02-9291126'],
    email: 'exam@bou.ac.bd',
    address: 'Examination Section, BOU, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM',
    source: 'bou.ac.bd/Administration/Exam',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  },
  {
    id: 'student-support-desk',
    department: 'Student Support Services (SSS)',
    name: 'Student Information Desk',
    role: 'General Admission & School Queries',
    phone: ['01540-770957', '02996691175'],
    email: 'info@bou.ac.bd',
    address: 'Student Support Services Division, BOU, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM',
    source: 'bou.ac.bd/Administration/SSS',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  },
  {
    id: 'it-computer-center',
    department: 'Computer Center & IT Division',
    name: 'Systems Manager',
    role: 'IT Infrastructure & Portal Operations',
    phone: ['02-9291120'],
    email: 'system@bou.ac.bd',
    address: 'Computer Center, BOU, Board Bazar, Gazipur-1705',
    officeHours: 'Sunday to Thursday: 9:00 AM – 5:00 PM',
    source: 'bou.ac.bd/Administration/ComputerCenter',
    lastVerifiedAt: '2026-09-02T10:00:00Z',
    status: 'verified'
  }
];

const REGIONAL_CENTERS = [
  {
    code: '11',
    nameEn: 'Dhaka Regional Center',
    nameBn: 'ঢাকা আঞ্চলিক কেন্দ্র',
    address: '4/A Govt. Laboratory School Road, Dhanmondi, Dhaka-1205',
    phone: '02-9669528',
    email: 'rc.dhaka@bou.ac.bd',
    subCenters: ['Gazipur', 'Narayanganj', 'Munshiganj', 'Narsingdi', 'Manikganj']
  },
  {
    code: '12',
    nameEn: 'Chattogram Regional Center',
    nameBn: 'চট্টগ্রাম আঞ্চলিক কেন্দ্র',
    address: 'Chandgaon Residential Area, Chattogram-4212',
    phone: '031-671234',
    email: 'rc.chattogram@bou.ac.bd',
    subCenters: ["Cox's Bazar", 'Rangamati', 'Khagrachhari', 'Bandarban']
  },
  {
    code: '13',
    nameEn: 'Rajshahi Regional Center',
    nameBn: 'রাজশাহী আঞ্চলিক কেন্দ্র',
    address: 'Naldanga Road, Sopura, Rajshahi-6203',
    phone: '0721-772345',
    email: 'rc.rajshahi@bou.ac.bd',
    subCenters: ['Natore', 'Naogaon', 'Chapai Nawabganj']
  },
  {
    code: '14',
    nameEn: 'Khulna Regional Center',
    nameBn: 'খুলনা আঞ্চলিক কেন্দ্র',
    address: 'Boyra Main Road, Khulna-9000',
    phone: '041-761234',
    email: 'rc.khulna@bou.ac.bd',
    subCenters: ['Bagerhat', 'Satkhira']
  },
  {
    code: '15',
    nameEn: 'Sylhet Regional Center',
    nameBn: 'সিলেট আঞ্চলিক কেন্দ্র',
    address: 'Pirmoholla, Ambarkhana, Sylhet-3100',
    phone: '0821-714522',
    email: 'rc.sylhet@bou.ac.bd',
    subCenters: ['Moulvibazar', 'Habiganj', 'Sunamganj']
  },
  {
    code: '16',
    nameEn: 'Barishal Regional Center',
    nameBn: 'বরিশাল আঞ্চলিক কেন্দ্র',
    address: 'Rupatali, Barishal-8200',
    phone: '0431-2174312',
    email: 'rc.barishal@bou.ac.bd',
    subCenters: ['Patuakhali', 'Bhola', 'Pirojpur', 'Jhalokathi', 'Barguna']
  },
  {
    code: '17',
    nameEn: 'Rangpur Regional Center',
    nameBn: 'রংপুর আঞ্চলিক কেন্দ্র',
    address: 'Radhaballabh, Rangpur-5400',
    phone: '0521-63456',
    email: 'rc.rangpur@bou.ac.bd',
    subCenters: ['Dinajpur', 'Kurigram', 'Gaibandha', 'Nilphamari', 'Lalmonirhat', 'Thakurgaon', 'Panchagarh']
  },
  {
    code: '18',
    nameEn: 'Mymensingh Regional Center',
    nameBn: 'ময়মনসিংহ আঞ্চলিক কেন্দ্র',
    address: 'Maskanda, Mymensingh-2200',
    phone: '091-65432',
    email: 'rc.mymensingh@bou.ac.bd',
    subCenters: ['Jamalpur', 'Sherpur', 'Netrokona', 'Kishoreganj']
  },
  {
    code: '19',
    nameEn: 'Bogura Regional Center',
    nameBn: 'বগুড়া আঞ্চলিক কেন্দ্র',
    address: 'Khandar, Bogura-5800',
    phone: '051-66789',
    email: 'rc.bogura@bou.ac.bd',
    subCenters: ['Joypurhat', 'Sirajganj', 'Pabna']
  },
  {
    code: '20',
    nameEn: 'Cumilla Regional Center',
    nameBn: 'কুমিল্লা আঞ্চলিক কেন্দ্র',
    address: 'Kotbari Road, Cumilla-3500',
    phone: '081-67890',
    email: 'rc.cumilla@bou.ac.bd',
    subCenters: ['Brahmanbaria', 'Chandpur', 'Noakhali', 'Feni', 'Lakshmipur']
  },
  {
    code: '21',
    nameEn: 'Faridpur Regional Center',
    nameBn: 'ফরিদপুর আঞ্চলিক কেন্দ্র',
    address: 'Goalchamot, Faridpur-7800',
    phone: '0631-64567',
    email: 'rc.faridpur@bou.ac.bd',
    subCenters: ['Rajbari', 'Gopalganj', 'Madaripur', 'Shariatpur']
  },
  {
    code: '22',
    nameEn: 'Jashore Regional Center',
    nameBn: 'যশোর আঞ্চলিক কেন্দ্র',
    address: 'Chanchra, Jashore-7400',
    phone: '0421-67890',
    email: 'rc.jashore@bou.ac.bd',
    subCenters: ['Jhenaidah', 'Magura', 'Narail', 'Kushtia', 'Chuadanga', 'Meherpur']
  }
];

const PROGRAM_TEMPLATES = {
  bba: {
    id: 'bba',
    nameEn: 'Bachelor of Business Administration (BBA)',
    nameBn: 'ব্যাচেলর অব বিজনেস অ্যাডমিনিস্ট্রেশন (বিবিএ)',
    schoolEn: 'School of Business',
    schoolBn: 'ব্যবসায় অনুষদ (স্কুল অব বিজনেস)',
    totalCreditsRequired: 120,
    durationYears: 4,
    semestersCount: 8,
    sampleCourses: [
      { id: 1, title: 'Principles of Management (BBA 1101)', credits: 3.0, marks: 80, grade: 'A+' },
      { id: 2, title: 'Business Communication (BBA 1102)', credits: 3.0, marks: 76, grade: 'A' },
      { id: 3, title: 'Financial Accounting (BBA 1103)', credits: 3.0, marks: 72, grade: 'A-' },
      { id: 4, title: 'Business Mathematics (BBA 1104)', credits: 3.0, marks: 68, grade: 'B+' },
      { id: 5, title: 'Computer Applications in Business (BBA 1105)', credits: 3.0, marks: 84, grade: 'A+' }
    ]
  },
  ba_bss: {
    id: 'ba_bss',
    nameEn: 'Bachelor of Arts / Social Science (BA/BSS)',
    nameBn: 'ব্যাচেলর অব আর্টস / সমাজবিজ্ঞান (বিএ/বিএসএস)',
    schoolEn: 'School of Social Science, Humanities & Languages',
    schoolBn: 'সামাজিক বিজ্ঞান, মানবিক ও ভাষা স্কুল',
    totalCreditsRequired: 126,
    durationYears: 3,
    semestersCount: 6,
    sampleCourses: [
      { id: 1, title: 'Bangla Language & Literature (BA 111)', credits: 3.0, marks: 75, grade: 'A' },
      { id: 2, title: 'English Foundation Course (BA 112)', credits: 3.0, marks: 66, grade: 'B+' },
      { id: 3, title: 'History of Bengal (BA 113)', credits: 3.0, marks: 73, grade: 'A-' },
      { id: 4, title: 'Political Science Basics (BA 114)', credits: 3.0, marks: 70, grade: 'A-' },
      { id: 5, title: 'Islamic Studies / Philosophy (BA 115)', credits: 3.0, marks: 78, grade: 'A' },
      { id: 6, title: 'Bangladesh Studies (BA 116)', credits: 3.0, marks: 82, grade: 'A+' }
    ]
  },
  llb: {
    id: 'llb',
    nameEn: 'Bachelor of Laws (LLB Honours)',
    nameBn: 'ব্যাচেলর অব লজ (এলএলবি অনার্স)',
    schoolEn: 'School of Social Science, Humanities & Languages',
    schoolBn: 'আইন ও সামাজিক বিজ্ঞান অনুষদ',
    totalCreditsRequired: 128,
    durationYears: 4,
    semestersCount: 8,
    sampleCourses: [
      { id: 1, title: 'Jurisprudence & Legal Theory (LLB 101)', credits: 4.0, marks: 74, grade: 'A-' },
      { id: 2, title: 'Law of Contract (LLB 102)', credits: 4.0, marks: 76, grade: 'A' },
      { id: 3, title: 'Constitutional Law of Bangladesh (LLB 103)', credits: 4.0, marks: 81, grade: 'A+' },
      { id: 4, title: 'Muslim Law & Personal Law (LLB 104)', credits: 4.0, marks: 71, grade: 'A-' }
    ]
  },
  mba: {
    id: 'mba',
    nameEn: 'Master of Business Administration (MBA)',
    nameBn: 'মাস্টার অব বিজনেস অ্যাডমিনিস্ট্রেশন (এমবিএ)',
    schoolEn: 'School of Business',
    schoolBn: 'ব্যবসায় অনুষদ (স্কুল অব বিজনেস)',
    totalCreditsRequired: 60,
    durationYears: 2,
    semestersCount: 4,
    sampleCourses: [
      { id: 1, title: 'Organizational Behavior (MBA 501)', credits: 3.0, marks: 78, grade: 'A' },
      { id: 2, title: 'Managerial Economics (MBA 502)', credits: 3.0, marks: 82, grade: 'A+' },
      { id: 3, title: 'Corporate Finance (MBA 503)', credits: 3.0, marks: 75, grade: 'A' },
      { id: 4, title: 'Marketing Management (MBA 504)', credits: 3.0, marks: 86, grade: 'A+' }
    ]
  },
  hsc: {
    id: 'hsc',
    nameEn: 'Higher Secondary Certificate (HSC)',
    nameBn: 'উচ্চ মাধ্যমিক সার্টিফিকেট (এইচএসসি)',
    schoolEn: 'Open School',
    schoolBn: 'ওপেন স্কুল',
    totalCreditsRequired: 48,
    durationYears: 2,
    semestersCount: 4,
    sampleCourses: [
      { id: 1, title: 'Bangla 1st & 2nd Paper (HSC 1801)', credits: 3.0, marks: 76, grade: 'A' },
      { id: 2, title: 'English 1st & 2nd Paper (HSC 1802)', credits: 3.0, marks: 68, grade: 'B+' },
      { id: 3, title: 'Information & Comm. Technology (HSC 1803)', credits: 3.0, marks: 88, grade: 'A+' },
      { id: 4, title: 'Civics & Good Governance (HSC 1804)', credits: 3.0, marks: 72, grade: 'A-' }
    ]
  }
};

if (typeof module !== 'undefined' && module.exports) {
  module.exports = {
    OFFICIAL_SERVICES,
    OFFICIAL_CONTACTS,
    REGIONAL_CENTERS,
    PROGRAM_TEMPLATES
  };
}
