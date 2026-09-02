package com.example.model

data class SupportHotline(
    val title: String,
    val number: String,
    val description: String,
    val availability: String
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
    val CENTRAL_HOTLINES = listOf(
        SupportHotline(
            title = "OSAPS Support Desk 1",
            number = "01635-020191",
            description = "Student login, password recovery & profile issues",
            availability = "Sat - Thu: 9:00 AM - 5:00 PM"
        ),
        SupportHotline(
            title = "OSAPS Support Desk 2",
            number = "01911-390048",
            description = "Online admission form submission & payment verification",
            availability = "Sat - Thu: 9:00 AM - 5:00 PM"
        ),
        SupportHotline(
            title = "OSAPS Support Desk 3",
            number = "01712-680456",
            description = "Technical glitch, SMS confirmation & ID card queries",
            availability = "Sat - Thu: 9:00 AM - 5:00 PM"
        ),
        SupportHotline(
            title = "BOU Central Helpline",
            number = "02-996691120",
            description = "Main campus Gazipur PABX operator & general queries",
            availability = "Sun - Thu: 9:00 AM - 4:00 PM"
        )
    )

    val CENTRAL_EMAILS = listOf(
        "osaps@bou.ac.bd" to "OSAPS Technical Helpdesk",
        "helpdesk@bou.ac.bd" to "BOU Student Support Services",
        "info@bou.ac.bd" to "General Information & Registry",
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
            answer = "Usually payment updates within 15-30 minutes. If it stays pending for over 24 hours, take a screenshot of your payment Transaction ID (TrxID) and send an email to osaps@bou.ac.bd or call the OSAPS technical support hotline.",
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
            answer = "BOU uses a 4.00 Grade Point scale for Undergraduate and Masters programs. Grade Point Average (GPA) = Sum of (Course Grade Point x Course Credits) / Total Credits. You can use the built-in 'Marks Calculator' in this app to compute your term GPA and cumulative CGPA instantly!",
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
