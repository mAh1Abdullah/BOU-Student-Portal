# Project Directives & Master Plan: BOU Student Companion

## 1. Master Plan Reference
All development on this project must strictly adhere to the authoritative specification and roadmap defined in **`/PLAN.md`** (*Bangladesh Open University (BOU) Student Companion: Production-Grade Project Development & Product Specification*).

## 2. Core Operational Constraints
1. **Never Impersonate BOU**: The application is an independent student utility dashboard for Bangladesh Open University students. Clearly indicate that official records and authoritative data come from BOU's official systems.
2. **Official-Source First**: Every official service link, academic rule, and contact must have a verified reference, with clear visual boundaries distinguishing "Official BOU Service" from "Student Utility".
3. **Accuracy & Determinism**: All academic calculations (GPA, CGPA, Attempted vs. Earned Credits, Degree Classification) must be deterministic, strictly adhering to BOU's 4.00 grading scale and program regulations.
4. **Privacy First**: Only store non-sensitive convenience data (Student ID, Program, Regional Center) locally in browser storage. Never collect or store OSAPS passwords, OTPs, or session tokens.
5. **Authentic Institutional Visual System**: The UI must reflect Bangladesh Open University's familiar institutional digital atmosphere (verified green `#006A4E` / primary tokens, clear bilingual Bengali & English typography with Noto Sans Bengali & English sans-serif, accessible WCAG 2.2 AA contrast, restrained academic cards rather than generic startup SaaS aesthetics).
6. **Bilingual Support**: Maintain high-quality natural Bengali (বাংলা) and English localization throughout all navigation, tools, and labels.
