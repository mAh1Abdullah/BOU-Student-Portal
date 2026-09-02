package com.example.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BouGradingStandards
import com.example.model.CourseInputMode
import com.example.model.CourseSubject
import com.example.model.GradingSystemType
import com.example.model.SavedCalculation
import com.example.ui.theme.BouPrimary
import com.example.ui.theme.BouPrimaryDark
import com.example.ui.theme.GradeAColor
import com.example.ui.theme.GradeBColor
import com.example.ui.theme.GradeCColor
import com.example.ui.theme.GradeFColor
import com.example.viewmodel.CalculatorUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksCalculatorScreen(
    state: CalculatorUiState,
    onGradingSystemChange: (GradingSystemType) -> Unit,
    onSemesterNameChange: (String) -> Unit,
    onAddCourse: () -> Unit,
    onRemoveCourse: (String) -> Unit,
    onUpdateCourse: (
        id: String,
        code: String?,
        title: String?,
        credits: Double?,
        marks: String?,
        gradeLetter: String?,
        inputMode: CourseInputMode?
    ) -> Unit,
    onPreviousCgpaChange: (String, String) -> Unit,
    onTargetCgpaChange: (String, String) -> Unit,
    onReset: () -> Unit,
    onSaveCalculation: (String) -> Unit,
    onDeleteSaved: (String) -> Unit,
    onOpenResultPortal: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var showGradingScaleDialog by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var saveTitleInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("calculator_screen")
    ) {
        // Tab Row (Term GPA, Cumulative CGPA, Saved Records)
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Term GPA", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                icon = { Icon(Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("CGPA Planner", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                icon = { Icon(Icons.Default.TrendingUp, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("History (${state.savedCalculations.size})", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                icon = { Icon(Icons.Default.History, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
        }

        when (selectedTab) {
            0 -> TermGpaTab(
                state = state,
                onGradingSystemChange = onGradingSystemChange,
                onSemesterNameChange = onSemesterNameChange,
                onAddCourse = onAddCourse,
                onRemoveCourse = onRemoveCourse,
                onUpdateCourse = onUpdateCourse,
                onReset = onReset,
                onOpenScaleInfo = { showGradingScaleDialog = true },
                onOpenResultPortal = onOpenResultPortal,
                onSave = {
                    saveTitleInput = "${state.semesterName} - GPA: ${state.result.gpa}"
                    showSaveDialog = true
                },
                onShare = {
                    val summary = buildSummaryText(state)
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "BOU GPA Result Breakdown")
                        putExtra(Intent.EXTRA_TEXT, summary)
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Result"))
                },
                onCopy = {
                    val summary = buildSummaryText(state)
                    clipboardManager.setText(AnnotatedString(summary))
                    Toast.makeText(context, "Result summary copied!", Toast.LENGTH_SHORT).show()
                }
            )

            1 -> CgpaPlannerTab(
                state = state,
                onPreviousCgpaChange = onPreviousCgpaChange,
                onTargetCgpaChange = onTargetCgpaChange
            )

            2 -> SavedHistoryTab(
                savedList = state.savedCalculations,
                onDelete = onDeleteSaved
            )
        }
    }

    if (showGradingScaleDialog) {
        GradingScaleReferenceDialog(
            gradingSystem = state.gradingSystem,
            onDismiss = { showGradingScaleDialog = false },
            onOpenResultPortal = onOpenResultPortal
        )
    }

    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Save GPA Calculation", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(
                        "Give this calculation a title to save it to your local records.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = saveTitleInput,
                        onValueChange = { saveTitleInput = it },
                        label = { Text("Calculation Title") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSaveCalculation(saveTitleInput)
                        showSaveDialog = false
                        Toast.makeText(context, "Calculation saved!", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("Save Record")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun TermGpaTab(
    state: CalculatorUiState,
    onGradingSystemChange: (GradingSystemType) -> Unit,
    onSemesterNameChange: (String) -> Unit,
    onAddCourse: () -> Unit,
    onRemoveCourse: (String) -> Unit,
    onUpdateCourse: (
        id: String,
        code: String?,
        title: String?,
        credits: Double?,
        marks: String?,
        gradeLetter: String?,
        inputMode: CourseInputMode?
    ) -> Unit,
    onReset: () -> Unit,
    onOpenScaleInfo: () -> Unit,
    onOpenResultPortal: () -> Unit,
    onSave: () -> Unit,
    onShare: () -> Unit,
    onCopy: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Result Summary Header Card
        item {
            GpaScoreDisplayCard(
                state = state,
                onOpenScaleInfo = onOpenScaleInfo,
                onSave = onSave,
                onShare = onShare,
                onCopy = onCopy
            )
        }

        // BOU Standard 4.00 Grading Guide & Formula Card
        item {
            BouStandardGuideCard(
                onOpenScaleInfo = onOpenScaleInfo,
                onOpenResultPortal = onOpenResultPortal
            )
        }

        // Grading Scale Selector
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Grading Scale",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = onOpenScaleInfo) {
                            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("BOU Scale Chart", fontSize = 12.sp)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = state.gradingSystem == GradingSystemType.HIGHER_EDUCATION_4_0,
                            onClick = { onGradingSystemChange(GradingSystemType.HIGHER_EDUCATION_4_0) },
                            label = { Text("4.00 Scale (BA, BBA, Masters)", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f)
                        )

                        FilterChip(
                            selected = state.gradingSystem == GradingSystemType.OPEN_SCHOOL_5_0,
                            onClick = { onGradingSystemChange(GradingSystemType.OPEN_SCHOOL_5_0) },
                            label = { Text("5.00 Scale (SSC/HSC)", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = state.semesterName,
                        onValueChange = onSemesterNameChange,
                        label = { Text("Semester / Term Label") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Courses Header & Add Button
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enrolled Courses (${state.courses.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedButton(
                        onClick = onReset,
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reset", fontSize = 12.sp)
                    }

                    Button(
                        onClick = onAddCourse,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add Course", fontSize = 12.sp)
                    }
                }
            }
        }

        // Course items list
        items(state.courses, key = { it.id }) { course ->
            CourseRowCard(
                course = course,
                gradingSystem = state.gradingSystem,
                onUpdate = { code, title, credits, marks, letter, mode ->
                    onUpdateCourse(course.id, code, title, credits, marks, letter, mode)
                },
                onDelete = { onRemoveCourse(course.id) }
            )
        }

        item {
            Button(
                onClick = onAddCourse,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Another Course / Subject", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun GpaScoreDisplayCard(
    state: CalculatorUiState,
    onOpenScaleInfo: () -> Unit,
    onSave: () -> Unit,
    onShare: () -> Unit,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maxGpa = state.gradingSystem.maxGpa
    val gpa = state.result.gpa

    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(BouPrimary, Color(0xFF004D40))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = state.semesterName.ifBlank { "Calculated Result" },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "BOU Grading Standard (${maxGpa} Scale)",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFFFD54F)
                        )
                    }

                    Surface(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (state.result.failedCount == 0) "All Passed" else "${state.result.failedCount} Failed",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = String.format("%.2f", gpa),
                                style = MaterialTheme.typography.displayMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Text(
                                text = " / ${String.format("%.2f", maxGpa)}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
                            )
                        }

                        Text(
                            text = state.result.classification,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD54F)
                        )
                    }

                    // Stat column
                    Column(horizontalAlignment = Alignment.End) {
                        StatBadge(label = "Attempted Credits", value = "${state.result.totalCreditsAttempted}")
                        Spacer(modifier = Modifier.height(4.dp))
                        StatBadge(label = "Earned Credits", value = "${state.result.totalCreditsEarned}")
                        Spacer(modifier = Modifier.height(4.dp))
                        StatBadge(label = "Total Points", value = "${state.result.totalGradePoints}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(
                            onClick = onCopy,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f))
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color.White, modifier = Modifier.size(18.dp))
                        }

                        IconButton(
                            onClick = onShare,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f))
                        ) {
                            Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    }

                    Button(
                        onClick = onSave,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFD54F),
                            contentColor = Color(0xFF00251A)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Save Result", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun StatBadge(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CourseRowCard(
    course: CourseSubject,
    gradingSystem: GradingSystemType,
    onUpdate: (
        code: String?,
        title: String?,
        credits: Double?,
        marks: String?,
        letter: String?,
        mode: CourseInputMode?
    ) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradePoint = course.calculateGradePoint(gradingSystem)
    val gradeLetter = course.getGradeLetter(gradingSystem)
    val gradeColor = when {
        gradePoint >= 3.5 -> GradeAColor
        gradePoint >= 3.0 -> GradeBColor
        gradePoint >= 2.0 -> GradeCColor
        else -> GradeFColor
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(gradeColor.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = gradeLetter,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = gradeColor
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "${course.courseCode.ifBlank { "Course" }} • ${course.creditHours} Credits",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Grade Point: ${String.format("%.2f", gradePoint)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = gradeColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Course",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Inputs row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = course.courseCode,
                    onValueChange = { onUpdate(it, null, null, null, null, null) },
                    label = { Text("Code") },
                    singleLine = true,
                    modifier = Modifier.weight(1.2f)
                )

                // Credit selector
                var creditExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = creditExpanded,
                    onExpandedChange = { creditExpanded = !creditExpanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = "${course.creditHours} cr",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Credits") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = creditExpanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    )

                    ExposedDropdownMenu(
                        expanded = creditExpanded,
                        onDismissRequest = { creditExpanded = false }
                    ) {
                        listOf(1.0, 1.5, 2.0, 3.0, 4.0, 6.0).forEach { cr ->
                            DropdownMenuItem(
                                text = { Text("$cr Credit${if (cr > 1.0) "s" else ""}") },
                                onClick = {
                                    onUpdate(null, null, cr, null, null, null)
                                    creditExpanded = false
                                }
                            )
                        }
                    }
                }

                // Mode switcher (Marks vs Grade)
                if (course.inputMode == CourseInputMode.BY_MARKS) {
                    OutlinedTextField(
                        value = course.marksObtained,
                        onValueChange = { onUpdate(null, null, null, it, null, null) },
                        label = { Text("Marks (0-100)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1.4f)
                    )
                } else {
                    var gradeExpanded by remember { mutableStateOf(false) }
                    val gradeLetters = if (gradingSystem == GradingSystemType.HIGHER_EDUCATION_4_0) {
                        listOf("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F")
                    } else {
                        listOf("A+", "A", "A-", "B", "C", "D", "F")
                    }

                    ExposedDropdownMenuBox(
                        expanded = gradeExpanded,
                        onExpandedChange = { gradeExpanded = !gradeExpanded },
                        modifier = Modifier.weight(1.4f)
                    ) {
                        OutlinedTextField(
                            value = course.selectedGradeLetter,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Grade") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = gradeExpanded) },
                            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        )

                        ExposedDropdownMenu(
                            expanded = gradeExpanded,
                            onDismissRequest = { gradeExpanded = false }
                        ) {
                            gradeLetters.forEach { letter ->
                                DropdownMenuItem(
                                    text = { Text(letter, fontWeight = FontWeight.Bold) },
                                    onClick = {
                                        onUpdate(null, null, null, null, letter, null)
                                        gradeExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Toggle input mode
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (course.inputMode == CourseInputMode.BY_MARKS) "Switch to Grade Letter input" else "Switch to Raw Marks input",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {
                            val newMode = if (course.inputMode == CourseInputMode.BY_MARKS) CourseInputMode.BY_GRADE_LETTER else CourseInputMode.BY_MARKS
                            onUpdate(null, null, null, null, null, newMode)
                        }
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun CgpaPlannerTab(
    state: CalculatorUiState,
    onPreviousCgpaChange: (String, String) -> Unit,
    onTargetCgpaChange: (String, String) -> Unit
) {
    var prevCgpa by remember { mutableStateOf(state.previousCgpa) }
    var prevCredits by remember { mutableStateOf(state.previousCredits) }
    var targetCgpa by remember { mutableStateOf(state.targetCgpa) }
    var remCredits by remember { mutableStateOf(state.remainingCredits) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Multi-Semester Cumulative CGPA Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Cumulative CGPA Calculator",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Combine your previous semesters' completed CGPA with current term GPA.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = prevCgpa,
                            onValueChange = {
                                prevCgpa = it
                                onPreviousCgpaChange(prevCgpa, prevCredits)
                            },
                            label = { Text("Previous CGPA") },
                            placeholder = { Text("e.g. 3.45") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = prevCredits,
                            onValueChange = {
                                prevCredits = it
                                onPreviousCgpaChange(prevCgpa, prevCredits)
                            },
                            label = { Text("Previous Credits") },
                            placeholder = { Text("e.g. 36.0") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    state.combinedCgpa?.let { combined ->
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Projected Cumulative CGPA",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Current Term + Previous Records",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 11.sp
                                    )
                                }

                                Text(
                                    text = String.format("%.2f", combined),
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Target CGPA Goal Planner Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Target CGPA Goal Planner",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Find out what GPA you need in remaining courses to graduate with your desired CGPA.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = targetCgpa,
                            onValueChange = {
                                targetCgpa = it
                                onTargetCgpaChange(targetCgpa, remCredits)
                            },
                            label = { Text("Target CGPA") },
                            placeholder = { Text("e.g. 3.75") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = remCredits,
                            onValueChange = {
                                remCredits = it
                                onTargetCgpaChange(targetCgpa, remCredits)
                            },
                            label = { Text("Remaining Credits") },
                            placeholder = { Text("e.g. 15.0") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    state.requiredGpaForTarget?.let { neededGpa ->
                        val isPossible = neededGpa <= state.gradingSystem.maxGpa && neededGpa >= 0.0
                        Surface(
                            color = if (isPossible) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.errorContainer,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (isPossible) "Required GPA in Remaining Courses" else "Goal Mathematically Out of Range",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isPossible) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onErrorContainer
                                    )

                                    Text(
                                        text = String.format("%.2f", neededGpa),
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (isPossible) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                                    )
                                }

                                Text(
                                    text = if (isPossible) "You need to maintain an average of at least ${String.format("%.2f", neededGpa)} in remaining $remCredits credits." else "Achieving a $targetCgpa CGPA is not mathematically feasible with the specified credit balance.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isPossible) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onErrorContainer,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SavedHistoryTab(
    savedList: List<SavedCalculation>,
    onDelete: (String) -> Unit
) {
    if (savedList.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Saved Calculations Yet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Calculate your term GPA in the 'Term GPA' tab and tap 'Save Result' to record it here.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(savedList, key = { it.id }) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "GPA: ${String.format("%.2f", item.gpa)} • ${item.totalCredits} Credits",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = item.coursesSummary,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp,
                                maxLines = 2
                            )
                        }

                        IconButton(onClick = { onDelete(item.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BouStandardGuideCard(
    onOpenScaleInfo: () -> Unit,
    onOpenResultPortal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color(0xFF90CAF9).copy(alpha = 0.6f), RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0F7FF)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0277BD).copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = Color(0xFF0277BD),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "BOU 4.00 Grading Standard",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                }

                Surface(
                    color = Color(0xFF0277BD).copy(alpha = 0.12f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Official Rule",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF0277BD),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Bangladesh Open University (BOU) calculates results and Cumulative Grade Point Average (CGPA) on a standard 4.00 point grading scale.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF334155),
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "CGPA Calculation Formula:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF004D40)
                    )
                    Text(
                        text = "CGPA = (Sum of (Credit Hours × Grade Point)) / (Sum of Credit Hours)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0F172A),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "To find your CGPA, multiply each course's grade point by its assigned credit hours, add them all up, and divide by the total number of credit hours.",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF64748B),
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Class Classifications Summary Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFE8F5E9)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("3.00 – 4.00", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color(0xFF1B5E20))
                        Text("First Class", fontSize = 10.sp, color = Color(0xFF2E7D32))
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFFF8E1)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("2.25 – 2.99", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color(0xFFE65100))
                        Text("Second Class", fontSize = 10.sp, color = Color(0xFFF57C00))
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF3E5F5)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Below 2.25", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color(0xFF6A1B9A))
                        Text("Third / Pass", fontSize = 10.sp, color = Color(0xFF7B1FA2))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onOpenScaleInfo,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Text("View Full Scale", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = onOpenResultPortal,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0277BD)),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Text("Result Portal", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun GradingScaleReferenceDialog(
    gradingSystem: GradingSystemType,
    onDismiss: () -> Unit,
    onOpenResultPortal: () -> Unit = {}
) {
    val scaleList = if (gradingSystem == GradingSystemType.HIGHER_EDUCATION_4_0) {
        BouGradingStandards.SCALE_4_0
    } else {
        BouGradingStandards.SCALE_5_0
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text("Bangladesh Open University (BOU)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = "Standard 4.00 Point Grading Scale & CGPA Formula",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "Bangladesh Open University (BOU) calculates results and Cumulative Grade Point Average (CGPA) on a standard 4.00 point grading scale.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                item {
                    Text(
                        text = "Grading Scale and Grade Points",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Marks Range", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                        Text("Grade", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                        Text("Grade Point", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                    }
                }

                items(scaleList) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item.marksRange, style = MaterialTheme.typography.bodySmall, fontSize = 12.sp)
                        Surface(
                            color = if (item.point >= 3.0) GradeAColor.copy(alpha = 0.15f) else if (item.point >= 2.0) GradeBColor.copy(alpha = 0.15f) else GradeFColor.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = item.letter,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (item.point >= 3.0) GradeAColor else if (item.point >= 2.0) GradeBColor else GradeFColor,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Text(String.format("%.2f", item.point), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                item {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = "CGPA Calculation Formula",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "To find your CGPA, multiply each course's grade point by its assigned credit hours, add them all up, and divide by the total number of credit hours:\n\nCGPA = (Σ (Credit Hours × Grade Point)) / (Σ Credit Hours)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                item {
                    Text(
                        text = "Class Classifications Based on CGPA",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "• 3.00 – 4.00: First Class\n• 2.25 – 2.99: Second Class\n• Below 2.25: Third Class / Pass",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Official Result Portal Notice",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "You can view your official grade sheets and notices directly through the Bangladesh Open University Result Portal.",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                lineHeight = 14.sp,
                                modifier = Modifier.padding(top = 2.dp, bottom = 6.dp)
                            )
                            Button(
                                onClick = {
                                    onDismiss()
                                    onOpenResultPortal()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(vertical = 4.dp)
                            ) {
                                Text("Open Result Portal (result.bou.ac.bd)", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

private fun buildSummaryText(state: CalculatorUiState): String {
    val sb = StringBuilder()
    sb.appendLine("🎓 Bangladesh Open University GPA Breakdown")
    sb.appendLine("Semester: ${state.semesterName}")
    sb.appendLine("Calculated GPA: ${String.format("%.2f", state.result.gpa)} / ${state.gradingSystem.maxGpa}")
    sb.appendLine("Classification: ${state.result.classification}")
    sb.appendLine("Credits Attempted: ${state.result.totalCreditsAttempted} (Earned: ${state.result.totalCreditsEarned})")
    sb.appendLine("Total Grade Points: ${state.result.totalGradePoints}")
    sb.appendLine("\nCourses:")
    for (c in state.courses) {
        sb.appendLine("• ${c.courseCode}: Grade ${c.getGradeLetter(state.gradingSystem)} (${c.creditHours} Credits)")
    }
    return sb.toString()
}
