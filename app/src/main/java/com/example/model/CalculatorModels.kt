package com.example.model

enum class GradingSystemType(val displayName: String, val maxGpa: Double) {
    HIGHER_EDUCATION_4_0("Undergraduate / Graduate (4.00 Scale)", 4.0),
    OPEN_SCHOOL_5_0("SSC / HSC Open School (5.00 Scale)", 5.0)
}

data class GradeDefinition(
    val letter: String,
    val point: Double,
    val marksRange: String,
    val minMarks: Double,
    val maxMarks: Double,
    val remarks: String
)

object BouGradingStandards {
    val SCALE_4_0 = listOf(
        GradeDefinition("A+", 4.00, "80% and above", 80.0, 100.0, "Outstanding / First Class"),
        GradeDefinition("A", 3.75, "75% to 79%", 75.0, 79.99, "Excellent / First Class"),
        GradeDefinition("A-", 3.50, "70% to 74%", 70.0, 74.99, "Very Good / First Class"),
        GradeDefinition("B+", 3.25, "65% to 69%", 65.0, 69.99, "Good / First Class"),
        GradeDefinition("B", 3.00, "60% to 64%", 60.0, 64.99, "Satisfactory / First Class"),
        GradeDefinition("B-", 2.75, "55% to 59%", 55.0, 59.99, "Above Average (varies by prog) / Second Class"),
        GradeDefinition("C+", 2.50, "50% to 54%", 50.0, 54.99, "Average / Second Class"),
        GradeDefinition("C", 2.25, "45% to 49%", 45.0, 49.99, "Pass / Second Class"),
        GradeDefinition("C-", 2.00, "40% to 44%", 40.0, 44.99, "Pass / Third Class"),
        GradeDefinition("F", 0.00, "Below 40%", 0.0, 39.99, "Fail")
    )

    val SCALE_5_0 = listOf(
        GradeDefinition("A+", 5.00, "80% - 100%", 80.0, 100.0, "Outstanding"),
        GradeDefinition("A", 4.00, "70% - 79%", 70.0, 79.99, "Excellent"),
        GradeDefinition("A-", 3.50, "60% - 69%", 60.0, 69.99, "Very Good"),
        GradeDefinition("B", 3.00, "50% - 59%", 50.0, 59.99, "Good"),
        GradeDefinition("C", 2.00, "40% - 49%", 40.0, 49.99, "Pass"),
        GradeDefinition("D", 1.00, "33% - 39%", 33.0, 39.99, "Poor Pass"),
        GradeDefinition("F", 0.00, "0% - 32%", 0.0, 32.99, "Fail")
    )

    fun getGradeFromMarks(marks: Double, scale: GradingSystemType): GradeDefinition {
        val list = if (scale == GradingSystemType.HIGHER_EDUCATION_4_0) SCALE_4_0 else SCALE_5_0
        return list.firstOrNull { marks >= it.minMarks && marks <= it.maxMarks }
            ?: if (marks >= 100.0) list.first() else list.last()
    }

    fun getGradeFromLetter(letter: String, scale: GradingSystemType): GradeDefinition {
        val list = if (scale == GradingSystemType.HIGHER_EDUCATION_4_0) SCALE_4_0 else SCALE_5_0
        return list.firstOrNull { it.letter.equals(letter.trim(), ignoreCase = true) }
            ?: list.last()
    }
}

enum class CourseInputMode {
    BY_MARKS,
    BY_GRADE_LETTER
}

data class CourseSubject(
    val id: String = java.util.UUID.randomUUID().toString(),
    val courseCode: String = "",
    val courseTitle: String = "",
    val creditHours: Double = 3.0,
    val inputMode: CourseInputMode = CourseInputMode.BY_MARKS,
    val marksObtained: String = "75",
    val selectedGradeLetter: String = "A",
    val isOptional: Boolean = false
) {
    fun calculateGradePoint(scale: GradingSystemType): Double {
        return when (inputMode) {
            CourseInputMode.BY_MARKS -> {
                val marks = marksObtained.toDoubleOrNull() ?: 0.0
                BouGradingStandards.getGradeFromMarks(marks, scale).point
            }
            CourseInputMode.BY_GRADE_LETTER -> {
                BouGradingStandards.getGradeFromLetter(selectedGradeLetter, scale).point
            }
        }
    }

    fun getGradeLetter(scale: GradingSystemType): String {
        return when (inputMode) {
            CourseInputMode.BY_MARKS -> {
                val marks = marksObtained.toDoubleOrNull() ?: 0.0
                BouGradingStandards.getGradeFromMarks(marks, scale).letter
            }
            CourseInputMode.BY_GRADE_LETTER -> {
                selectedGradeLetter
            }
        }
    }
}

data class GpaCalculationResult(
    val gpa: Double = 0.0,
    val totalCreditsAttempted: Double = 0.0,
    val totalCreditsEarned: Double = 0.0,
    val totalGradePoints: Double = 0.0,
    val classification: String = "",
    val passedCount: Int = 0,
    val failedCount: Int = 0
)

data class SavedCalculation(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val semesterName: String,
    val timestamp: Long = System.currentTimeMillis(),
    val gpa: Double,
    val totalCredits: Double,
    val gradingSystem: GradingSystemType,
    val coursesSummary: String
)
