# Bangladesh Open University (BOU) Student Companion
## Production-Grade Project Development & Product Specification

> **Product positioning:** A student utility dashboard for Bangladesh Open University (BOU) students.  
> **Important:** Unless formally authorized by BOU, the application must not present itself as an official BOU product or imply institutional endorsement.

---

# 1. Product Overview

The **BOU Student Companion** is a centralized student utility application that helps BOU students quickly access official BOU services and use practical academic tools from one organized dashboard.

The application should combine:

- Official BOU service links
- Academic calculation utilities
- Student profile shortcuts
- Academic progress tools
- Examination utilities
- Administrative contact information
- BOU-specific student resources

The application does **not** replace BOU's official systems such as OSAPS, the BOU website, examination-result systems, admission systems, or official notice boards.

## Core Product Loop

```text
FIND
  ↓
UNDERSTAND
  ↓
CALCULATE / PREPARE
  ↓
ACCESS OFFICIAL SERVICE
  ↓
COMPLETE TASK
```

---

# 2. Product Principles

1. **Official-source first**
2. **Utility before decoration**
3. **Accuracy before convenience**
4. **Privacy before personalization**
5. **Never impersonate BOU**
6. **Never collect unnecessary credentials**
7. **Transparent external-service boundaries**
8. **Accessible by default**
9. **Mobile-first**
10. **Graceful failure when external BOU services are unavailable**
11. **Configurable academic rules rather than hidden hard-coded assumptions**
12. **Every important calculation must be deterministic and testable**

---

# 3. BOU-Authentic Visual System

The visual identity must be based on the existing **Bangladesh Open University (BOU)** and **OSAPS** digital environment. The goal is familiarity rather than a generic modern SaaS appearance:

> **A BOU student should immediately feel that this application belongs to the same digital ecosystem as the official BOU services.**

The official BOU website (`www.bou.ac.bd`) and OSAPS (`osapsnew.bou.ac.bd`) are the primary visual references. Reuse the institutional visual language and atmosphere, but do **not** copy their exact page layouts, source code, or interface.

## 3.1 Visual Character

The interface should feel:

- Institutional
- Academic
- Familiar
- Practical
- Trustworthy
- Government/education-service oriented
- Clean and modern without becoming a fashionable SaaS product

Avoid:

- Apple/Linear/Stripe-style branding
- Excessive glassmorphism
- Neon colors
- Large decorative gradients
- Excessive rounded cards
- Dark-mode-first presentation
- "Startup dashboard" aesthetics
- Decorative elements that compete with academic information

The modernization should improve **clarity and usability**, not replace BOU's visual identity.

## 3.2 Color Philosophy

Do not invent a completely new palette.

The existing BOU/OSAPS interface should be treated as the visual source of truth. The implementation should first sample/verify the colors actually used by the current official interfaces and then encode them as design tokens.

### Recommended Semantic Tokens

| Token | Purpose |
|---|---|
| `bou-primary` | Main institutional green |
| `bou-primary-dark` | Darker green for hover, active navigation, and headings |
| `bou-primary-light` | Light green background/tint |
| `bou-secondary` | Institutional blue/navy used by the reference interfaces |
| `bou-accent` | Secondary institutional accent where present |
| `bou-danger` | Error/critical status |
| `bou-warning` | Warning/pending status |
| `bou-success` | Success/available status |
| `bou-surface` | Main card/content surface |
| `bou-background` | Main page background |
| `bou-border` | Subtle structural border |
| `bou-text` | Primary text |
| `bou-text-muted` | Secondary text |

### Color Rules

1. **Institutional green is the primary visual anchor.**
2. Blue/navy should support the hierarchy rather than compete with green.
3. Red should be reserved for errors, urgent information, and official warning states.
4. Gold/yellow should be used sparingly for highlights or achievement indicators.
5. Prefer white/light neutral backgrounds for the main application.
6. Do not use gradients as a primary branding technique.
7. Do not use saturated decorative colors simply to make the interface look modern.
8. All colors must pass accessibility contrast requirements.

### Important Implementation Rule

Do not treat previously proposed values such as `#006A4E`, `#0B2545`, `#C62828`, or `#FFB300` as mandatory final brand values merely because they were previously listed.

Instead:

```text
Official BOU Website
        +
Official OSAPS Interface
        ↓
Visual Audit
        ↓
Verified Color Tokens
        ↓
Application Design System
```

This ensures the application stays visually aligned if the official BOU interfaces use slightly different shades.

## 3.3 Navigation

The navigation should feel closer to an educational/government portal than a commercial SaaS application.

Recommended:

- Institutional green header/navigation
- Clear Bengali + English labels
- Simple menu structure
- Strong active-state indicator
- Familiar dropdown/menu patterns
- Official-service grouping
- Utility grouping

Example:

```text
BOU Student Companion
│
├── Dashboard
├── Academic
│   ├── GPA Calculator
│   ├── CGPA Calculator
│   ├── Credit Progress
│   └── CGPA Predictor
├── Official Services
│   ├── BOU Website
│   ├── Results
│   ├── Notices
│   ├── Admission
│   ├── OSAPS
│   └── Ebook
└── Help & Contacts
```

## 3.4 Header

The header should establish immediate BOU familiarity.

Include:

- BOU Student Companion name
- Appropriate institutional-style visual treatment
- English/Bangla language switcher
- Navigation
- Optional compact profile shortcut

Do not reproduce the exact official BOU header.

If the BOU logo is used, it must be used only where legally/officially permitted. Otherwise, use a neutral product mark with an academic/institutional treatment.

## 3.5 Buttons

Primary buttons should use the verified BOU institutional green.

Examples:

```text
[ Calculate GPA ]
[ Open OSAPS ]
[ Check Result ]
[ View Notice ]
```

Button hierarchy:

- Primary → institutional green
- Secondary → white/light surface + institutional border
- Destructive → red
- Informational → institutional blue
- Disabled → neutral gray

Avoid pill-shaped buttons everywhere. Use conventional rectangular or moderately rounded controls consistent with the reference interfaces.

## 3.6 Cards & Bento Grid

The Bento grid remains part of the product, but it should be **BOU-styled rather than startup-styled**.

Cards should use:

- White/light surfaces
- Thin institutional/neutral borders
- Small or restrained corner radius
- Minimal shadows
- Institutional icon accents
- Clear Bengali/English hierarchy

Official services should visibly carry:

> **Official BOU Service**

The card should feel like a convenient shortcut inside an academic portal—not like a marketing tile.

## 3.7 Typography

Typography should prioritize readability for Bangla-speaking students.

Recommended:

- **Bangla:** Noto Sans Bengali or Hind Siliguri
- **English:** Inter or another highly readable sans-serif

Use consistent hierarchy:

```text
Page Title
Section Title
Card Title
Body
Supporting Text
Caption
```

Avoid excessively large marketing-style headings.

## 3.8 Forms

Forms should resemble familiar institutional/academic systems:

- Clear labels above fields
- Conventional input controls
- Visible required states
- Helpful validation messages
- Logical grouping
- Adequate spacing
- Strong focus states

For the calculator:

```text
Course Name
Course Code
Credit Hours
Input Type
Marks / Grade
```

The interface should prioritize completing the calculation quickly over visual decoration.

## 3.9 Status Badges

Use restrained status badges:

- **Official BOU Service**
- **Available**
- **Checking**
- **Unavailable**
- **Last checked**
- **Local Data**

Do not use badges simply for visual decoration.

## 3.10 Official-vs-Independent Visual Boundary

The application should visually distinguish:

### Official BOU Services

Links to:

- BOU website
- OSAPS
- Results
- Notices
- Admission
- Handbooks
- Ebook

### Application Utilities

Features created by this application:

- GPA calculator
- CGPA calculator
- CGPA predictor
- Credit tracker
- Local student profile
- Result organizer

Use labels such as:

> **Official BOU Service**

and:

> **Student Utility**

This prevents users from confusing calculated or locally stored information with official academic records.

## 3.11 Responsive Behavior

The visual system must preserve the familiar institutional feeling across devices.

### Mobile

- Compact green header
- Large readable Bengali labels
- One-column utility cards
- Sticky or easily accessible navigation
- Large touch targets

### Tablet

- Two-column utility layout
- Expanded navigation where appropriate

### Desktop

- Institutional-style header/navigation
- Spacious but practical content area
- Bento shortcuts
- Academic dashboard cards

Do not turn the desktop layout into a highly stylized dashboard with excessive whitespace.

## 3.12 Design System Tokens

Recommended implementation:

```css
:root {
  --bou-primary: /* verified BOU green */;
  --bou-primary-dark: /* verified darker green */;
  --bou-primary-light: /* verified light green */;
  --bou-secondary: /* verified BOU blue/navy */;
  --bou-danger: /* verified/error */;
  --bou-warning: /* verified/warning */;
  --bou-success: /* verified/success */;

  --bou-background: #f7f7f7;
  --bou-surface: #ffffff;
  --bou-border: #dddddd;
  --bou-text: #222222;
  --bou-text-muted: #666666;
}
```

The actual institutional color values should be documented in a single configuration file after auditing the current official BOU and OSAPS interfaces.

## 3.13 Visual QA Checklist

Before release, compare the application against the current official BOU and OSAPS interfaces for:

- Primary green
- Secondary colors
- Header treatment
- Button appearance
- Typography character
- Border treatment
- Form styling
- Navigation patterns
- Status colors
- Overall visual density

The result should pass this subjective test:

> **"If a BOU student opens this after using the official BOU website or OSAPS, does the application feel familiar rather than like an unrelated third-party product?"**

---

# 4. Portal Hub

The dashboard should provide a Bento-style quick-access hub.

## Official Services

### 4.1 BOU Main Website

- URL: `www.bou.ac.bd`
- Label: **BOU Main Website**
- Bengali: **বাউবি মূল ওয়েবসাইট**
- Type: Official BOU service

### 4.2 Examination Results

- URL: `result.bou.ac.bd`
- Label: **Examination Results**
- Bengali: **পরীক্ষার ফলাফল**
- Type: Official BOU service

### 4.3 Notice Board

- URL: `www.bou.ac.bd/NoticeBoard`
- Label: **Official Notice Board**
- Bengali: **অফিসিয়াল নোটিশ বোর্ড**
- Type: Official BOU service

### 4.4 Online Admission

- URL: `www.bou.ac.bd/BOU/Admission`
- Label: **Online Admission**
- Bengali: **অনলাইন ভর্তি**
- Type: Official BOU service

### 4.5 Student Handbooks

- URL: `www.bou.ac.bd/BOU/StudentHandbook`
- Label: **Student Handbooks & Academic Rules**
- Bengali: **শিক্ষার্থী নির্দেশিকা ও একাডেমিক নিয়মাবলি**
- Type: Official BOU service

### 4.6 OSAPS

- URL: `osapsnew.bou.ac.bd`
- Label: **OSAPS Student Profile**
- Bengali: **OSAPS শিক্ষার্থী প্রোফাইল**
- Type: Official BOU service

### 4.7 Ebook Library

- URL: `www.ebookbou.edu.bd`
- Label: **Ebook Library & Textbooks**
- Bengali: **ই-বুক লাইব্রেরি ও পাঠ্যপুস্তক**
- Type: Official BOU resource

## External Service Status

Do **not** permanently display "Online" merely because a URL exists.

Recommended statuses:

- `Available`
- `Unavailable`
- `Checking`
- `Unknown`
- `Last checked: <timestamp>`

If availability checks are not implemented, use a neutral label such as:

> **Official Service**

---

# 5. Academic Calculator System

The calculator is a core utility and must be implemented as a standalone, thoroughly tested calculation engine.

## 5.1 BOU 4.00 Grade Scale

| Grade | Grade Point | Marks |
|---|---:|---:|
| A+ | 4.00 | 80%+ |
| A | 3.75 | 75–79% |
| A- | 3.50 | 70–74% |
| B+ | 3.25 | 65–69% |
| B | 3.00 | 60–64% |
| B- | 2.75 | 55–59% |
| C+ | 2.50 | 50–54% |
| C | 2.25 | 45–49% |
| C- | 2.00 | 40–44% |
| F | 0.00 | Below 40% |

> **Verification requirement:** Academic rules must be verified against the current applicable BOU program/regulation before being presented as official. Do not assume that one grading/classification rule applies universally to every BOU program.

## 5.2 Input Modes

Support:

- Exact marks
- Letter grade
- Credit hours
- Course name/code
- Optional semester
- Optional course status

## 5.3 Calculation Modes

Provide separate tools for:

### Course Grade

```text
Marks
  ↓
Grade
  ↓
Grade Point
```

### Semester GPA

```text
Σ(Grade Point × Credit)
-----------------------
       Σ(Credit)
```

### Cumulative CGPA

```text
Σ(All Grade Point × Credit)
---------------------------
        Σ(All Credits)
```

### Attempted vs Earned Credits

Track:

- Total attempted credits
- Successfully earned credits
- Failed credits
- Remaining credits

Do not treat failed/attempted credits as earned.

---

# 6. Grade & Calculation Engine Architecture

Keep academic rules outside UI components.

Recommended structure:

```text
src/
├── features/
│   └── academics/
│       ├── calculator/
│       │   ├── gradeScale.ts
│       │   ├── calculations.ts
│       │   ├── validation.ts
│       │   └── classifications.ts
│       ├── curriculum/
│       └── progress/
├── data/
│   └── bou/
│       ├── programs.ts
│       ├── gradingRules.ts
│       └── officialServices.ts
└── components/
```

Academic configuration should contain:

```ts
{
  programId,
  effectiveFrom,
  effectiveTo,
  gradingScale,
  classificationRules,
  creditRequirements,
  repeatCoursePolicy
}
```

This prevents future BOU regulation changes from requiring UI rewrites.

---

# 7. Program Curriculum Templates

The application may provide templates for:

- BBA
- BA/BSS
- LLB
- MBA
- HSC

However:

> Templates are convenience tools and must not be represented as the student's official academic record.

Each curriculum should contain:

- Program name
- Program code
- Total required credits
- Course list
- Course codes
- Credit values
- Semester/level
- Elective requirements
- Effective academic session
- Source/reference
- Last verified date

If the curriculum cannot be verified, mark it:

> **Reference template — verify with your current BOU program documents.**

---

# 8. Degree Classification

Classification calculations must be configurable.

Do not permanently hard-code assumptions such as:

```text
CGPA >= X → First Class
```

Instead:

```text
Program
  ↓
Academic Regulation Version
  ↓
Classification Rules
  ↓
Calculated Result
```

Display:

- Calculated CGPA
- Applicable classification rule
- Whether the rule was verified
- Reference/version
- Disclaimer when necessary

---

# 9. Student OSAPS Profile Card

The local profile card may store:

- Student ID
- Program
- Regional Center

It may provide:

- Copy Student ID
- Copy Program
- Copy Regional Center
- Open official OSAPS
- Edit profile
- Clear local profile

## Privacy Rules

Do **not** store:

- OSAPS password
- OTP
- Authentication tokens
- Secret answers
- Payment information
- Unnecessary personal information

Do not proxy OSAPS authentication.

### Storage Recommendation

For non-sensitive convenience data:

```text
localStorage
```

is acceptable when clearly explained to users.

For anything more sensitive:

- Prefer in-memory state
- Consider encrypted local storage where appropriate
- Avoid server-side storage unless there is a compelling product requirement

## Privacy UX

Provide:

> **Your profile is stored on this device for convenience. It is not your official BOU record.**

Provide:

- Clear all saved data
- Privacy explanation
- Optional local-data reset

---

# 10. Academic Dashboard

The home dashboard should evolve beyond a link collection.

Recommended cards:

### Academic Overview

- Current CGPA
- Total earned credits
- Remaining credits
- Current semester

### Quick Actions

- Calculate GPA
- Calculate CGPA
- Check Results
- Open OSAPS
- View Notices
- Open Ebook Library

### Progress

```text
Credits Earned
████████████░░░░
72 / 120 credits
```

### Important Notice

Show a neutral link to the official BOU notice board rather than scraping or reproducing official notices unless a reliable, authorized data source exists.

---

# 11. Future Academic Utilities

After the core calculator is stable, consider:

## 11.1 Required GPA Calculator

Example:

> "I currently have 3.20 CGPA. What GPA do I need in my remaining credits to reach 3.50?"

## 11.2 CGPA Predictor

Allow scenarios:

```text
Current CGPA
+
Future course grades
+
Credit hours
↓
Projected CGPA
```

Clearly label results as **projections**, not official results.

## 11.3 Marks-to-Grade Converter

Input:

```text
Marks: 73
```

Output:

```text
Grade: A-
Grade Point: 3.50
```

## 11.4 Degree Progress Tracker

Track:

- Required credits
- Completed credits
- Remaining credits
- Failed courses
- Planned courses

## 11.5 Result Organizer

Allow users to manually enter official results and organize them locally.

---

# 12. Helpline & Administrative Directory

Store contacts as structured data rather than embedding them throughout components.

## Primary OSAPS Helplines

- `01618977237`
- `01907451612`
- Availability: Office hours only

## OSAPS Support Email

- `help.osaps@bou.ac.bd`

## Registrar Administration

- **T M Ahmed Hussain**
- Board Bazar, Gazipur-1705
- `registrar@bou.ac.bd`

## Admission & Examination

Student Support Desk:

- `01540-770957`
- `02996691175`

Examination & Certificates:

- `01758931163`

Regional center information should be maintained separately and verified periodically.

> **Verification requirement:** Contact information is time-sensitive. Store `source`, `lastVerifiedAt`, and `status` for every contact.

---

# 13. Contact Data Model

Recommended:

```ts
interface OfficialContact {
  id: string;
  department: string;
  name?: string;
  role?: string;
  phone?: string[];
  email?: string;
  address?: string;
  officeHours?: string;
  source: string;
  lastVerifiedAt: string;
  status: "verified" | "needs_review";
}
```

This allows outdated contacts to be identified without silently presenting stale information.

---

# 14. Official Service Data Model

```ts
interface OfficialService {
  id: string;
  titleEn: string;
  titleBn: string;
  descriptionEn: string;
  descriptionBn: string;
  url: string;
  category: "website" | "academic" | "admission" | "result" | "library";
  official: true;
  status: "available" | "unavailable" | "checking" | "unknown";
  lastCheckedAt?: string;
}
```

---

# 15. Security Requirements

## Never collect

- OSAPS passwords
- OTPs
- Authentication tokens
- Payment-card data
- Unnecessary identity documents

## External Links

Use safe external-link behavior:

- HTTPS where available
- Clear destination labels
- External-link indicators
- Do not silently redirect
- Do not embed login pages in insecure iframes

## Content Integrity

Do not allow untrusted external content to be rendered as trusted BOU content.

---

# 16. Accessibility

Target:

**WCAG 2.2 AA**

Requirements:

- Keyboard navigation
- Visible focus states
- Semantic HTML
- Screen-reader labels
- Accessible form validation
- Sufficient contrast
- Reduced-motion support
- Large touch targets
- Error messages that explain how to recover
- Bangla screen-reader compatibility testing
- No information conveyed by color alone

---

# 17. Localization

Support:

- English
- বাংলা

Use natural Bangla rather than literal machine translation.

Example:

```text
Calculate CGPA
CGPA হিসাব করুন
```

Maintain translation keys separately from application logic.

Recommended:

```text
i18n/
├── en/
└── bn/
```

---

# 18. Responsive Design

The application should be mobile-first.

## Mobile

Prioritize:

1. Quick actions
2. CGPA/GPA calculator
3. OSAPS shortcut
4. Results
5. Notices
6. Contacts

## Tablet

Use a two-column dashboard.

## Desktop

Use:

```text
Sidebar / Navigation
        +
Main Dashboard
        +
Bento Utility Grid
```

Avoid excessive cards. Group related utilities.

---

# 19. Visual Design System

The design should feel:

- Institutional
- Modern
- Trustworthy
- Clean
- Academic
- Lightweight

Avoid:

- Excessive gradients
- Excessive shadows
- Overloaded dashboards
- Tiny text
- Excessive animation
- Unnecessary decorative elements

Use:

- Strong hierarchy
- Generous whitespace
- Clear section labels
- Consistent iconography
- Restrained institutional colors
- Clear status badges

---

# 20. Error & Offline States

External BOU systems can become unavailable.

Example:

```text
Official BOU service is currently unavailable.

You can try again later or visit the service directly.
```

The application itself should continue working for:

- GPA calculations
- CGPA calculations
- Saved local profile
- Locally stored academic records
- Static resources

---

# 21. Testing Strategy

## Unit Tests

Test:

- Every grade boundary
- Marks below 40
- Exactly 40
- Exactly 45
- Exactly 50
- Exactly 55
- Exactly 60
- Exactly 65
- Exactly 70
- Exactly 75
- Exactly 80
- Invalid marks
- Negative marks
- Marks above maximum
- Zero-credit courses
- Fractional credits
- Mixed grade/mark inputs
- Failed courses
- Attempted vs earned credits

## Example Boundary Tests

```text
79 → A
80 → A+

74 → A-
75 → A

69 → B+
70 → A-

64 → B
65 → B+

59 → B-
60 → B

54 → C+
55 → B-

49 → C
50 → C+

44 → C-
45 → C

39 → F
40 → C-
```

The exact mapping must remain aligned with the verified BOU grading rule configuration.

## End-to-End Tests

Test:

- Add course
- Edit course
- Delete course
- Switch marks/grade input
- Calculate GPA
- Calculate CGPA
- Save profile
- Clear profile
- Open external service
- Language switching
- Mobile navigation

---

# 22. Performance

Goals:

- Fast first load
- Minimal JavaScript
- Lazy-load non-critical features
- Avoid unnecessary client-side rendering
- Optimize icons and images
- Cache static application data
- Keep the calculator usable without network access

Prefer local computation for academic calculations.

---

# 23. Recommended Technical Architecture

A production implementation can use:

```text
Frontend
├── React
├── Next.js
├── TypeScript
├── Tailwind CSS
└── PWA

Application Layer
├── Academic calculation engine
├── Curriculum configuration
├── Official service registry
├── Contact registry
├── Localization
└── Privacy/local-storage layer

Optional Backend
├── PostgreSQL
├── Secure API
├── Admin verification system
└── Content/configuration management
```

A backend is **not required** for the first version if the product only contains static official links, local utilities, and local profile storage.

---

# 24. Configuration-Driven Architecture

Keep BOU information in configuration files.

Example:

```text
config/
├── grading/
├── programs/
├── services/
├── contacts/
├── translations/
└── app/
```

Benefits:

- Easier maintenance
- Safer updates
- Versioned academic rules
- Less duplicated code
- Easier testing

---

# 25. Source & Verification System

Every official-data item should have:

```ts
{
  source,
  lastVerifiedAt,
  verificationStatus
}
```

Recommended status:

```text
verified
needs_review
outdated
```

The admin/maintenance workflow should periodically review:

- URLs
- Phone numbers
- Emails
- Academic rules
- Grading scale
- Program curricula
- Degree classification rules

---

# 26. Important Legal & Product Boundary

The application must clearly communicate:

> **This application is an independent student utility tool. Official academic records, results, admission information, notices, and student-account information are provided by Bangladesh Open University through its official systems.**

Do not:

- Claim official BOU ownership without authorization
- Modify official BOU records
- Scrape protected student information
- Circumvent OSAPS authentication
- Store BOU credentials
- Present calculated CGPA as an official academic result

---

# 27. Recommended Dashboard Structure

```text
┌─────────────────────────────────────────────┐
│ BOU Student Companion                       │
│ Student Utility Dashboard                   │
├─────────────────────────────────────────────┤
│                                             │
│ Welcome / Student Profile                   │
│ Student ID • Program • Regional Center      │
│                                             │
├──────────────┬──────────────┬───────────────┤
│ CGPA         │ Credits      │ Quick Access  │
│ 3.42         │ 72 / 120     │ OSAPS         │
├──────────────┴──────────────┴───────────────┤
│                                             │
│ Academic Tools                              │
│ ┌──────────┐ ┌──────────┐ ┌──────────────┐ │
│ │ GPA      │ │ CGPA     │ │ GPA Target   │ │
│ └──────────┘ └──────────┘ └──────────────┘ │
│                                             │
│ Official BOU Services                       │
│ ┌────────┐ ┌────────┐ ┌────────┐            │
│ │Results │ │Notices │ │Admission│           │
│ └────────┘ └────────┘ └────────┘            │
│                                             │
│ Resources                                   │
│ Ebook • Handbooks • Contacts                │
└─────────────────────────────────────────────┘
```

---

# 28. Development Roadmap

## Phase 0 — Product Boundary

- Finalize independent-product positioning
- Add official-service disclaimer
- Define data/privacy boundaries

## Phase 1 — Data Accuracy

- Verify official URLs
- Verify grading rules
- Verify contacts
- Verify program templates
- Add source and last-verified metadata

## Phase 2 — Core Architecture

- Separate academic engine from UI
- Create configuration-driven service registry
- Create contact registry
- Create curriculum configuration

## Phase 3 — Academic Engine

- Marks-to-grade converter
- GPA calculator
- CGPA calculator
- Credit tracking
- Validation
- Automated tests

## Phase 4 — Student Profile

- Local profile storage
- Clipboard actions
- OSAPS shortcut
- Clear-data control
- Privacy messaging

## Phase 5 — Dashboard

- Academic overview
- Quick actions
- Bento service hub
- Resources
- Contacts

## Phase 6 — Academic Progress

- Degree progress
- CGPA prediction
- Required GPA
- Result organizer

## Phase 7 — Accessibility & Localization

- English
- Bangla
- WCAG 2.2 AA improvements
- Keyboard testing
- Mobile testing

## Phase 8 — Reliability

- External service status
- Offline functionality
- Error states
- Retry mechanisms

## Phase 9 — Security Review

- Storage audit
- External-link audit
- Dependency audit
- XSS review
- CSP/security headers where applicable

## Phase 10 — Production QA

- Unit tests
- Integration tests
- E2E tests
- Cross-browser testing
- Android/mobile testing
- Performance testing

---

# 29. MVP Scope

The first production release should contain:

### Must Have

- BOU service hub
- GPA calculator
- CGPA calculator
- Verified 4.00 grade scale
- Local student profile
- Official contacts
- Bangla/English UI
- Mobile responsive interface
- Privacy controls
- Disclaimer
- Automated calculation tests

### Should Have

- Credit tracker
- Required GPA calculator
- CGPA projection
- Degree progress tracker
- Offline/PWA support

### Later

- Automated result import, only where officially and technically permissible
- Advanced academic planning
- Notifications
- Personalized study planning
- Native mobile applications

---

# 30. Non-Goals

The application should not initially attempt to:

- Replace OSAPS
- Replace the BOU website
- Become an unofficial student-account backend
- Store passwords
- Become a social network
- Scrape every BOU page
- Reproduce protected BOU systems
- Present unofficial data as institutional data

---

# 31. Final Product Vision

The long-term goal is:

> **One reliable student workspace for navigating BOU life without pretending to be BOU.**

A successful product should make common student tasks dramatically easier:

```text
"Where do I check my result?"
        ↓
Official Results

"Where do I access OSAPS?"
        ↓
Official OSAPS

"What grade did I get?"
        ↓
Marks → Grade

"What is my GPA?"
        ↓
GPA Calculator

"What CGPA can I reach?"
        ↓
CGPA Predictor

"How many credits have I completed?"
        ↓
Academic Progress

"Who do I contact?"
        ↓
Verified Contact Directory
```

The product should remain a **trusted utility layer around official BOU services**, with accuracy, privacy, accessibility, and maintainability taking priority over feature quantity.
