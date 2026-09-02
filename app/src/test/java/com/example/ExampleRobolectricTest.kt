package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.BouGradingStandards
import com.example.model.CourseSubject
import com.example.model.GradingSystemType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("BOU Student Portal", appName)
  }

  @Test
  fun `test BOU grading scale calculation`() {
    val courseA = CourseSubject(courseCode = "BOU-101", creditHours = 3.0, marksObtained = "85")
    val gradeA = courseA.calculateGradePoint(GradingSystemType.HIGHER_EDUCATION_4_0)
    assertEquals(4.0, gradeA, 0.01)

    val courseB = CourseSubject(courseCode = "BOU-102", creditHours = 3.0, marksObtained = "62")
    val gradeB = courseB.calculateGradePoint(GradingSystemType.HIGHER_EDUCATION_4_0)
    assertEquals(3.0, gradeB, 0.01)
  }
}
