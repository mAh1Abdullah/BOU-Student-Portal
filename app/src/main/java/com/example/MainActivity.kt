package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.UniversityLogoEmblem
import com.example.ui.screens.BrowserScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MarksCalculatorScreen
import com.example.ui.screens.SupportScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppTab
import com.example.viewmodel.PortalViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        BouAppRoot()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BouAppRoot(viewModel: PortalViewModel = viewModel()) {
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
  val browserState by viewModel.browserState.collectAsStateWithLifecycle()
  val calculatorState by viewModel.calculatorState.collectAsStateWithLifecycle()
  val savedCredential by viewModel.savedCredentials.collectAsStateWithLifecycle()
  val divisionFilter by viewModel.supportDivisionFilter.collectAsStateWithLifecycle()
  val faqSearchQuery by viewModel.faqSearchQuery.collectAsStateWithLifecycle()

  // If user is on a secondary tab and presses back, go back to Home
  BackHandler(enabled = currentTab != AppTab.HOME && !browserState.canGoBack) {
    viewModel.setTab(AppTab.HOME)
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      if (currentTab != AppTab.BROWSER) {
        CenterAlignedTopAppBar(
          title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
              UniversityLogoEmblem(size = 32)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = when (currentTab) {
                  AppTab.HOME -> "BOU Student Portal"
                  AppTab.CALCULATOR -> "Marks & GPA Calculator"
                  AppTab.SUPPORT -> "Technical Support Desk"
                  AppTab.BROWSER -> "OSAPS Portal"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
              )
            }
          },
          colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
          )
        )
      }
    },
    bottomBar = {
      NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
      ) {
        NavigationBarItem(
          selected = currentTab == AppTab.HOME,
          onClick = { viewModel.setTab(AppTab.HOME) },
          icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
          label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
          modifier = Modifier.testTag("nav_item_home")
        )
        NavigationBarItem(
          selected = currentTab == AppTab.BROWSER,
          onClick = { viewModel.setTab(AppTab.BROWSER) },
          icon = { Icon(Icons.Default.Language, contentDescription = "Web Portal") },
          label = { Text("OSAPS Web", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
          modifier = Modifier.testTag("nav_item_browser")
        )
        NavigationBarItem(
          selected = currentTab == AppTab.CALCULATOR,
          onClick = { viewModel.setTab(AppTab.CALCULATOR) },
          icon = { Icon(Icons.Default.Calculate, contentDescription = "Calculator") },
          label = { Text("Calculator", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
          modifier = Modifier.testTag("nav_item_calculator")
        )
        NavigationBarItem(
          selected = currentTab == AppTab.SUPPORT,
          onClick = { viewModel.setTab(AppTab.SUPPORT) },
          icon = { Icon(Icons.Default.ContactSupport, contentDescription = "Support") },
          label = { Text("Support", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
          modifier = Modifier.testTag("nav_item_support")
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Crossfade(targetState = currentTab, label = "tab_transition") { tab ->
        when (tab) {
          AppTab.HOME -> {
            HomeScreen(
              savedCredential = savedCredential,
              onPortalClick = { destination ->
                viewModel.openPortal(destination)
              },
              onSaveCredentials = { studentId, program, center, note ->
                viewModel.saveStudentCredentials(studentId, program, center, note)
              }
            )
          }

          AppTab.BROWSER -> {
            BrowserScreen(
              browserState = browserState,
              savedCredential = savedCredential,
              onUrlChange = { url, title ->
                viewModel.openUrlInBrowser(url, title)
              },
              onLoadingChange = { isLoading, progress ->
                viewModel.updateBrowserLoading(isLoading, progress)
              },
              onPageInfoChange = { title, url, canBack, canFwd ->
                viewModel.updateBrowserPageInfo(title, url, canBack, canFwd)
              },
              onDesktopModeToggle = {
                viewModel.toggleDesktopMode()
              },
              onError = { error ->
                viewModel.setBrowserError(error)
              }
            )
          }

          AppTab.CALCULATOR -> {
            MarksCalculatorScreen(
              state = calculatorState,
              onGradingSystemChange = { viewModel.setGradingSystem(it) },
              onSemesterNameChange = { viewModel.setSemesterName(it) },
              onAddCourse = { viewModel.addCourse() },
              onRemoveCourse = { viewModel.removeCourse(it) },
              onUpdateCourse = { id, code, title, credits, marks, letter, mode ->
                viewModel.updateCourse(id, code, title, credits, marks, letter, mode)
              },
              onPreviousCgpaChange = { cgpa, creds ->
                viewModel.setPreviousCgpa(cgpa, creds)
              },
              onTargetCgpaChange = { target, creds ->
                viewModel.setTargetCgpa(target, creds)
              },
              onReset = { viewModel.resetCalculator() },
              onSaveCalculation = { title -> viewModel.saveCurrentCalculation(title) },
              onDeleteSaved = { id -> viewModel.deleteSavedCalculation(id) },
              onOpenResultPortal = {
                viewModel.openUrlInBrowser("https://result.bou.ac.bd/", "BOU Result Portal")
              }
            )
          }

          AppTab.SUPPORT -> {
            SupportScreen(
              savedCredential = savedCredential,
              divisionFilter = divisionFilter,
              faqSearchQuery = faqSearchQuery,
              onDivisionFilterChange = { viewModel.setDivisionFilter(it) },
              onFaqSearchQueryChange = { viewModel.setFaqSearchQuery(it) }
            )
          }
        }
      }
    }
  }
}

