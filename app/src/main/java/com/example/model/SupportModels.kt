package com.example.model

data class SupportHotline(
    val title: String,
    val number: String,
    val description: String,
    val availability: String,
    val category: String = "Helpline"
)

data class AdministrativeContact(
    val name: String,
    val designation: String,
    val organization: String,
    val address: String,
    val phones: List<String>,
    val email: String,
    val website: String
)

data class RegionalCenter(
    val name: String,
    val division: String,
    val address: String,
    val phone: String,
    val email: String,
    val code: String
)

data class FaqItem(
    val id: String,
    val question: String,
    val questionBengali: String,
    val answer: String,
    val category: String
)

object SupportData {
    // Primary Help Line
    val PRIMARY_HELPLINES = listOf(
        SupportHotline(
            title = "Helpline Mobile 1",
            number = "01618977237",
            description = "OSAPS student profile, login & system help",
            availability = "During office hours only (without holidays)",
            category = "Helpline"
        ),
        SupportHotline(
            title = "Helpline Mobile 2",
            number = "01907451612",
            description = "Technical support & payment validation",
            availability = "During office hours only (without holidays)",
            category = "Helpline"
        )
    )

    // For Admission & Registration
    val ADMISSION_REGISTRATION_CONTACTS = listOf(
        SupportHotline(
            title = "Student Support Service (Mobile)",
            number = "01540-770957",
            description = "Admission guidance, registration & student affairs",
            availability = "During office hours only (without holidays)",
            category = "Admission & Registration"
        ),
        SupportHotline(
            title = "Student Support Service (Phone)",
            number = "02996691175",
            description = "Landline desk for registration & program queries",
            availability = "During office hours only (without holidays)",
            category = "Admission & Registration"
        ),
        SupportHotline(
            title = "ICT Unit (OSAPS) - Line 1",
            number = "01618977237",
            description = "Online admission portal & system assistance",
            availability = "During office hours only (without holidays)",
            category = "ICT Unit"
        ),
        SupportHotline(
            title = "ICT Unit (OSAPS) - Line 2",
            number = "01907451612",
            description = "OSAPS portal, technical & payment queries",
            availability = "During office hours only (without holidays)",
            category = "ICT Unit"
        ),
        SupportHotline(
            title = "Examination & Certificate",
            number = "01758931163",
            description = "Exam routine, result scrutiny & certificate release",
            availability = "During office hours only (without holidays)",
            category = "Exam & Certificate"
        )
    )

    // Administrative Contact
    val ADMIN_CONTACT = AdministrativeContact(
        name = "T M Ahmed Hussain",
        designation = "Registrar (In Charge)",
        organization = "Bangladesh Open University",
        address = "Board Bazar, Gazipur-1705, Bangladesh",
        phones = listOf("88-02996691112", "09666730730 Ex.105"),
        email = "registrar@bou.ac.bd",
        website = "www.bou.ac.bd"
    )

    val CENTRAL_HOTLINES = PRIMARY_HELPLINES + ADMISSION_REGISTRATION_CONTACTS

    val CENTRAL_EMAILS = listOf(
        "help.osaps@bou.ac.bd" to "OSAPS Technical Helpdesk (Primary)",
        "registrar@bou.ac.bd" to "Office of the Registrar",
        "info@bou.ac.bd" to "General University Information",
        "controller@bou.ac.bd" to "Office of the Controller of Examinations"
    )

    val REGIONAL_CENTERS = listOf(
        RegionalCenter(
            name = "Dhaka Regional Center",
            division = "Dhaka",
            address = "4/Ka, Government Laboratory High School Road, Dhanmondi, Dhaka-1205",
            phone = "02-9669528",
            email = "rcdhaka@bou.ac.bd",
            code = "RC-01"
        ),
        RegionalCenter(
            name = "Chattogram Regional Center",
            division = "Chattogram",
            address = "Nasirabad Housing Society, Road # 2, Nasirabad, Chattogram",
            phone = "031-651234",
            email = "rcchittagong@bou.ac.bd",
            code = "RC-02"
        ),
        RegionalCenter(
            name = "Rajshahi Regional Center",
            division = "Rajshahi",
            address = "Nawdapara, Sopura, Rajshahi-6203",
            phone = "0721-761324",
            email = "rcrajshahi@bou.ac.bd",
            code = "RC-03"
        ),
        RegionalCenter(
            name = "Khulna Regional Center",
            division = "Khulna",
            address = "Sonadanga Main Road, Mujgunni, Khulna-9000",
            phone = "041-760312",
            email = "rckhulna@bou.ac.bd",
            code = "RC-04"
        ),
        RegionalCenter(
            name = "Sylhet Regional Center",
            division = "Sylhet",
            address = "Pir Mahallah, Sylhet Sadar, Sylhet-3100",
            phone = "0821-714523",
            email = "rcsylhet@bou.ac.bd",
            code = "RC-05"
        ),
        RegionalCenter(
            name = "Barishal Regional Center",
            division = "Barishal",
            address = "Rupatoli, Barishal-8200",
            phone = "0431-217890",
            email = "rcbarisal@bou.ac.bd",
            code = "RC-06"
        ),
        RegionalCenter(
            name = "Rangpur Regional Center",
            division = "Rangpur",
            address = "Radhaballav, Rangpur Sadar, Rangpur-5400",
            phone = "0521-63456",
            email = "rcrangpur@bou.ac.bd",
            code = "RC-07"
        ),
        RegionalCenter(
            name = "Mymensingh Regional Center",
            division = "Mymensingh",
            address = "Maskanda, Mymensingh-2200",
            phone = "091-66780",
            email = "rcmymensingh@bou.ac.bd",
            code = "RC-08"
        ),
        RegionalCenter(
            name = "Cumilla Regional Center",
            division = "Chattogram",
            address = "Kotbari Road, Cumilla-3500",
            phone = "081-67890",
            email = "rccomilla@bou.ac.bd",
            code = "RC-09"
        ),
        RegionalCenter(
            name = "Bogura Regional Center",
            division = "Rajshahi",
            address = "Fulbari, Bogura-5800",
            phone = "051-69876",
            email = "rcbogra@bou.ac.bd",
            code = "RC-10"
        ),
        RegionalCenter(
            name = "Jashore Regional Center",
            division = "Khulna",
            address = "Chanchra, Jashore-7400",
            phone = "0421-65432",
            email = "rcjessore@bou.ac.bd",
            code = "RC-11"
        )
    )

    val FAQS = listOf(
        FaqItem(
            id = "faq_1",
            question = "How do I log in to OSAPS Student Profile?",
            questionBengali = "কিভাবে OSAPS স্টুডেন্ট প্রোফাইলে লগইন করব?",
            answer = "Go to osapsnew.bou.ac.bd or tap 'Student Profile' in this app. Enter your Student ID (e.g., 20-0-11-001-001) and your registered password. If you forgot your password, use the 'Forgot Password' link to receive an OTP on your registered mobile number.",
            category = "OSAPS Login"
        ),
        FaqItem(
            id = "faq_2",
            question = "Payment completed via bKash/Nagad/Rocket but status still Pending?",
            questionBengali = "বিকাশ/নগদ/রকেটে ফি প্রদান করেছি কিন্তু স্ট্যাটাস পেন্ডিং দেখাচ্ছে?",
            answer = "Usually payment updates within 15-30 minutes. If it stays pending for over 24 hours, take a screenshot of your payment Transaction ID (TrxID) and send an email to help.osaps@bou.ac.bd or call the OSAPS helpline (01618977237, 01907451612).",
            category = "Payment"
        ),
        FaqItem(
            id = "faq_3",
            question = "Where can I view and download my Exam Admit Card?",
            questionBengali = "পরীক্ষার এডমিট কার্ড কোথা থেকে ডাউনলোড করব?",
            answer = "Log in to your OSAPS profile. Under the Examination tab, you will find 'Admit Card Download' once the examination office releases the admit card for your registered semester.",
            category = "Examinations"
        ),
        FaqItem(
            id = "faq_4",
            question = "How is BOU CGPA calculated?",
            questionBengali = "বাউবি-র সিজিপিএ কিভাবে হিসাব করা হয়?",
            answer = "BOU calculates results on a standard 4.00 point grading scale. CGPA = Sum of (Course Grade Point x Course Credits) / Total Credits. You can use the built-in 'Marks Calculation' tool in this app to compute your term GPA and cumulative CGPA instantly!",
            category = "Grading & Results"
        ),
        FaqItem(
            id = "faq_5",
            question = "How to apply for Re-scrutiny / Result Review?",
            questionBengali = "ফলাফল পুনর্নিরীক্ষণ বা খাতা চ্যালেঞ্জ করার নিয়ম কি?",
            answer = "Result re-scrutiny applications must be submitted via OSAPS or prescribed form at your respective Regional Center within the announced deadline (usually within 15 to 30 days after result publication) along with the requisite fee.",
            category = "Examinations"
        )
    )
}
