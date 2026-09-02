package com.example.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.BouGradingStandards
import com.example.model.CourseInputMode
import com.example.model.CourseSubject
import com.example.model.GpaCalculationResult
import com.example.model.GradingSystemType
import com.example.model.PortalData
import com.example.model.PortalDestination
import com.example.model.SavedCalculation
import com.example.model.SavedStudentCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AppTab {
    HOME,
    BROWSER,
    CALCULATOR,
    SUPPORT
}

data class BrowserState(
    val currentUrl: String = "https://osapsnew.bou.ac.bd",
    val title: String = "OSAPS Student Profile",
    val isLoading: Boolean = false,
    val progress: Int = 0,
    val canGoBack: Boolean = false,
    val canGoForward: Boolean = false,
    val isDesktopMode: Boolean = false,
    val errorMessage: String? = null
)

data class CalculatorUiState(
    val gradingSystem: GradingSystemType = GradingSystemType.HIGHER_EDUCATION_4_0,
    val courses: List<CourseSubject> = defaultCourses(),
    val result: GpaCalculationResult = GpaCalculationResult(),
    val previousCgpa: String = "",
    val previousCredits: String = "",
    val combinedCgpa: Double? = null,
    val targetCgpa: String = "3.50",
    val remainingCredits: String = "15.0",
    val requiredGpaForTarget: Double? = null,
    val savedCalculations: List<SavedCalculation> = emptyList(),
    val semesterName: String = "Semester 1"
)

private fun defaultCourses(): List<CourseSubject> = listOf(
    CourseSubject(courseCode = "BOU-101", courseTitle = "Course 1", creditHours = 3.0, marksObtained = "78"),
    CourseSubject(courseCode = "BOU-102", courseTitle = "Course 2", creditHours = 3.0, marksObtained = "82"),
    CourseSubject(courseCode = "BOU-103", courseTitle = "Course 3", creditHours = 3.0, marksObtained = "72"),
    CourseSubject(courseCode = "BOU-104", courseTitle = "Course 4", creditHours = 3.0, marksObtained = "68")
)

class PortalViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("bou_student_prefs", Context.MODE_PRIVATE)

    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _browserState = MutableStateFlow(BrowserState())
    val browserState: StateFlow<BrowserState> = _browserState.asStateFlow()

    private val _calculatorState = MutableStateFlow(CalculatorUiState())
    val calculatorState: StateFlow<CalculatorUiState> = _calculatorState.asStateFlow()

    private val _savedCredentials = MutableStateFlow(SavedStudentCredential())
    val savedCredentials: StateFlow<SavedStudentCredential> = _savedCredentials.asStateFlow()

    private val _supportDivisionFilter = MutableStateFlow("All")
    val supportDivisionFilter: StateFlow<String> = _supportDivisionFilter.asStateFlow()

    private val _faqSearchQuery = MutableStateFlow("")
    val faqSearchQuery: StateFlow<String> = _faqSearchQuery.asStateFlow()

    init {
        loadSavedCredentials()
        recalculateGpa()
    }

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun openPortal(destination: PortalDestination) {
        if (destination.isInternalTool) {
            when (destination.id) {
                "marks_calculator" -> _currentTab.value = AppTab.CALCULATOR
                "contact_support" -> _currentTab.value = AppTab.SUPPORT
            }
        } else {
            _browserState.update {
                it.copy(
                    currentUrl = destination.url,
                    title = destination.title,
                    isLoading = true,
                    progress = 0,
                    errorMessage = null
                )
            }
            _currentTab.value = AppTab.BROWSER
        }
    }

    fun openUrlInBrowser(url: String, title: String) {
        _browserState.update {
            it.copy(
                currentUrl = url,
                title = title,
                isLoading = true,
                progress = 0,
                errorMessage = null
            )
        }
        _currentTab.value = AppTab.BROWSER
    }

    fun updateBrowserLoading(isLoading: Boolean, progress: Int) {
        _browserState.update {
            it.copy(isLoading = isLoading, progress = progress)
        }
    }

    fun updateBrowserPageInfo(title: String, url: String, canGoBack: Boolean, canGoForward: Boolean) {
        _browserState.update {
            it.copy(
                title = if (title.isNotBlank()) title else it.title,
                currentUrl = if (url.isNotBlank()) url else it.currentUrl,
                canGoBack = canGoBack,
                canGoForward = canGoForward,
                errorMessage = null
            )
        }
    }

    fun toggleDesktopMode() {
        _browserState.update {
            it.copy(isDesktopMode = !it.isDesktopMode)
        }
    }

    fun setBrowserError(error: String?) {
        _browserState.update { it.copy(errorMessage = error, isLoading = false) }
    }

    // Credentials management
    private fun loadSavedCredentials() {
        val studentId = prefs.getString("student_id", "") ?: ""
        val programName = prefs.getString("program_name", "") ?: ""
        val regionalCenter = prefs.getString("regional_center", "") ?: ""
        val note = prefs.getString("note", "") ?: ""
        _savedCredentials.value = SavedStudentCredential(studentId, programName, regionalCenter, note)
    }

    fun saveStudentCredentials(studentId: String, programName: String, regionalCenter: String, note: String) {
        prefs.edit()
            .putString("student_id", studentId.trim())
            .putString("program_name", programName.trim())
            .putString("regional_center", regionalCenter.trim())
            .putString("note", note.trim())
            .apply()
        _savedCredentials.value = SavedStudentCredential(studentId.trim(), programName.trim(), regionalCenter.trim(), note.trim())
    }

    // Marks Calculator Logic
    fun setGradingSystem(system: GradingSystemType) {
        _calculatorState.update { it.copy(gradingSystem = system) }
        recalculateGpa()
    }

    fun setSemesterName(name: String) {
        _calculatorState.update { it.copy(semesterName = name) }
    }

    fun addCourse() {
        val count = _calculatorState.value.courses.size + 1
        val newCourse = CourseSubject(
            courseCode = "BOU-${100 + count}",
            courseTitle = "Subject $count",
            creditHours = 3.0,
            marksObtained = "75"
        )
        _calculatorState.update { it.copy(courses = it.courses + newCourse) }
        recalculateGpa()
    }

    fun removeCourse(id: String) {
        _calculatorState.update { state ->
            val updated = state.courses.filter { it.id != id }
            state.copy(courses = if (updated.isEmpty()) listOf(CourseSubject()) else updated)
        }
        recalculateGpa()
    }

    fun updateCourse(
        id: String,
        code: String? = null,
        title: String? = null,
        credits: Double? = null,
        marks: String? = null,
        gradeLetter: String? = null,
        inputMode: CourseInputMode? = null
    ) {
        _calculatorState.update { state ->
            val updatedCourses = state.courses.map { course ->
                if (course.id == id) {
                    course.copy(
                        courseCode = code ?: course.courseCode,
                        courseTitle = title ?: course.courseTitle,
                        creditHours = credits ?: course.creditHours,
                        marksObtained = marks ?: course.marksObtained,
                        selectedGradeLetter = gradeLetter ?: course.selectedGradeLetter,
                        inputMode = inputMode ?: course.inputMode
                    )
                } else course
            }
            state.copy(courses = updatedCourses)
        }
        recalculateGpa()
    }

    fun setPreviousCgpa(cgpa: String, credits: String) {
        _calculatorState.update { it.copy(previousCgpa = cgpa, previousCredits = credits) }
        calculateCombinedCgpa()
    }

    fun setTargetCgpa(target: String, remainingCreds: String) {
        _calculatorState.update { it.copy(targetCgpa = target, remainingCredits = remainingCreds) }
        calculateTargetRequirement()
    }

    private fun recalculateGpa() {
        val state = _calculatorState.value
        val scale = state.gradingSystem
        var totalAttemptedCredits = 0.0
        var totalEarnedCredits = 0.0
        var totalWeightedPoints = 0.0
        var passed = 0
        var failed = 0

        for (course in state.courses) {
            val credits = course.creditHours
            val point = course.calculateGradePoint(scale)
            totalAttemptedCredits += credits
            totalWeightedPoints += (point * credits)
            if (point > 0.0) {
                totalEarnedCredits += credits
                passed++
            } else {
                failed++
            }
        }

        val gpa = if (totalAttemptedCredits > 0) totalWeightedPoints / totalAttemptedCredits else 0.0
        val classification = when {
            gpa >= 3.00 -> "First Class (3.00 – 4.00)"
            gpa >= 2.25 -> "Second Class (2.25 – 2.99)"
            gpa >= 2.00 -> "Third Class / Pass (Below 2.25)"
            else -> if (failed > 0 || gpa < 2.00) "Below 2.25 / Retake Needed" else "Pass"
        }

        val result = GpaCalculationResult(
            gpa = Math.round(gpa * 100.0) / 100.0,
            totalCreditsAttempted = totalAttemptedCredits,
            totalCreditsEarned = totalEarnedCredits,
            totalGradePoints = Math.round(totalWeightedPoints * 100.0) / 100.0,
            classification = classification,
            passedCount = passed,
            failedCount = failed
        )

        _calculatorState.update { it.copy(result = result) }
        calculateCombinedCgpa()
        calculateTargetRequirement()
    }

    private fun calculateCombinedCgpa() {
        val state = _calculatorState.value
        val prevCgpaVal = state.previousCgpa.toDoubleOrNull()
        val prevCreditsVal = state.previousCredits.toDoubleOrNull()
        val currentGpa = state.result.gpa
        val currentCredits = state.result.totalCreditsAttempted

        if (prevCgpaVal != null && prevCreditsVal != null && prevCreditsVal > 0) {
            val totalPoints = (prevCgpaVal * prevCreditsVal) + (currentGpa * currentCredits)
            val totalCredits = prevCreditsVal + currentCredits
            val combined = if (totalCredits > 0) totalPoints / totalCredits else 0.0
            _calculatorState.update { it.copy(combinedCgpa = Math.round(combined * 100.0) / 100.0) }
        } else {
            _calculatorState.update { it.copy(combinedCgpa = null) }
        }
    }

    private fun calculateTargetRequirement() {
        val state = _calculatorState.value
        val target = state.targetCgpa.toDoubleOrNull()
        val remCredits = state.remainingCredits.toDoubleOrNull()
        val prevCgpa = state.previousCgpa.toDoubleOrNull()
        val prevCredits = state.previousCredits.toDoubleOrNull()

        if (target != null && remCredits != null && remCredits > 0 && prevCgpa != null && prevCredits != null) {
            val totalRequiredPoints = target * (prevCredits + remCredits)
            val alreadyEarnedPoints = prevCgpa * prevCredits
            val neededPointsInRem = totalRequiredPoints - alreadyEarnedPoints
            val neededGpa = neededPointsInRem / remCredits
            _calculatorState.update { it.copy(requiredGpaForTarget = Math.round(neededGpa * 100.0) / 100.0) }
        } else {
            _calculatorState.update { it.copy(requiredGpaForTarget = null) }
        }
    }

    fun resetCalculator() {
        _calculatorState.update {
            it.copy(
                courses = defaultCourses(),
                previousCgpa = "",
                previousCredits = "",
                combinedCgpa = null,
                requiredGpaForTarget = null
            )
        }
        recalculateGpa()
    }

    fun saveCurrentCalculation(title: String) {
        val state = _calculatorState.value
        val summary = state.courses.joinToString(", ") { "${it.courseCode} (${it.getGradeLetter(state.gradingSystem)})" }
        val item = SavedCalculation(
            title = if (title.isNotBlank()) title else "${state.semesterName} Calculation",
            semesterName = state.semesterName,
            gpa = state.result.gpa,
            totalCredits = state.result.totalCreditsAttempted,
            gradingSystem = state.gradingSystem,
            coursesSummary = summary
        )
        _calculatorState.update { it.copy(savedCalculations = listOf(item) + it.savedCalculations) }
    }

    fun deleteSavedCalculation(id: String) {
        _calculatorState.update { it.copy(savedCalculations = it.savedCalculations.filter { item -> item.id != id }) }
    }

    // Support screen state
    fun setDivisionFilter(division: String) {
        _supportDivisionFilter.value = division
    }

    fun setFaqSearchQuery(query: String) {
        _faqSearchQuery.value = query
    }
}
