package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

enum class PortalCategory {
    MAIN,
    STUDENT,
    ADMISSION,
    EXAM,
    TOOLS,
    SUPPORT
}

data class PortalDestination(
    val id: String,
    val title: String,
    val bengaliTitle: String,
    val description: String,
    val url: String,
    val icon: ImageVector,
    val category: PortalCategory,
    val isInternalTool: Boolean = false,
    val badge: String? = null
)

object PortalData {
    val STUDENT_PROFILE = PortalDestination(
        id = "student_profile",
        title = "Student Profile",
        bengaliTitle = "শিক্ষার্থী প্রোফাইল (OSAPS)",
        description = "Login to your OSAPS profile, enrolled courses, student ID card & info",
        url = "https://osapsnew.bou.ac.bd/",
        icon = Icons.Default.AccountCircle,
        category = PortalCategory.STUDENT,
        badge = "OSAPS Login"
    )

    val HOME_PAGE = PortalDestination(
        id = "bou_home",
        title = "Website Home",
        bengaliTitle = "মূল ওয়েবসাইট",
        description = "Official Bangladesh Open University main portal and campus updates",
        url = "https://www.bou.ac.bd/",
        icon = Icons.Default.Language,
        category = PortalCategory.MAIN,
        badge = "Official"
    )

    val RESULT_PAGE = PortalDestination(
        id = "result_page",
        title = "Result Page",
        bengaliTitle = "পরীক্ষার ফলাফল",
        description = "Check semester final results, official grade sheets & published marks",
        url = "https://result.bou.ac.bd/",
        icon = Icons.Default.School,
        category = PortalCategory.EXAM,
        badge = "Official Result"
    )

    val NOTICE_BOARD = PortalDestination(
        id = "notice_board",
        title = "Notice Board",
        bengaliTitle = "নোটিশ বোর্ড",
        description = "Official BOU announcements, academic calendar & exam routines",
        url = "https://www.bou.ac.bd/NoticeBoard/",
        icon = Icons.Default.EventNote,
        category = PortalCategory.MAIN,
        badge = "Notices"
    )

    val ONLINE_ADMISSION = PortalDestination(
        id = "online_admission",
        title = "Online Admission",
        bengaliTitle = "অনলাইন ভর্তি",
        description = "Apply online for SSC, HSC, Undergrad, Masters & Diploma admissions",
        url = "https://www.bou.ac.bd/BOU/Admission",
        icon = Icons.Default.Assignment,
        category = PortalCategory.ADMISSION,
        badge = "Admissions"
    )

    val STUDENT_HANDBOOK = PortalDestination(
        id = "student_handbook",
        title = "Student Handbooks",
        bengaliTitle = "শিক্ষার্থী হ্যান্ডবুক",
        description = "Rules, regulations, curriculum guidelines and academic instructions",
        url = "https://www.bou.ac.bd/BOU/StudentHandbook",
        icon = Icons.Default.Description,
        category = PortalCategory.STUDENT,
        badge = "Handbooks"
    )

    val EBOOK_LIBRARY = PortalDestination(
        id = "ebook_library",
        title = "Ebook Library",
        bengaliTitle = "ই-বুক লাইব্রেরি",
        description = "Read & download free PDF course textbooks and open learning materials",
        url = "https://www.ebookbou.edu.bd/",
        icon = Icons.Default.MenuBook,
        category = PortalCategory.STUDENT,
        badge = "Textbooks"
    )

    val MARKS_CALCULATOR = PortalDestination(
        id = "marks_calculator",
        title = "Marks Calculation",
        bengaliTitle = "গ্রেড ও সিজিপিএ ক্যালকুলেটর",
        description = "Official BOU standard 4.00 grading scale, formula & CGPA calculator",
        url = "internal://calculator",
        icon = Icons.Default.Calculate,
        category = PortalCategory.TOOLS,
        isInternalTool = true,
        badge = "4.00 Scale"
    )

    val CONTACT_SUPPORT = PortalDestination(
        id = "contact_support",
        title = "Contact Support",
        bengaliTitle = "কারিগরি সহায়তা ও যোগাযোগ",
        description = "Technical assistance, OSAPS hotlines, emails & regional center directory",
        url = "internal://support",
        icon = Icons.Default.ContactSupport,
        category = PortalCategory.SUPPORT,
        isInternalTool = true,
        badge = "Helpdesk"
    )

    val ALL_PORTALS = listOf(
        STUDENT_PROFILE,
        HOME_PAGE,
        RESULT_PAGE,
        NOTICE_BOARD,
        ONLINE_ADMISSION,
        STUDENT_HANDBOOK,
        EBOOK_LIBRARY,
        MARKS_CALCULATOR,
        CONTACT_SUPPORT
    )

    val QUICK_BROWSER_LINKS = listOf(
        STUDENT_PROFILE,
        HOME_PAGE,
        RESULT_PAGE,
        NOTICE_BOARD,
        ONLINE_ADMISSION,
        STUDENT_HANDBOOK,
        EBOOK_LIBRARY
    )
}

data class SavedStudentCredential(
    val studentId: String = "",
    val programName: String = "",
    val regionalCenter: String = "",
    val note: String = ""
)
