package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = BouPrimaryDark,
    onPrimary = BouOnPrimaryDark,
    primaryContainer = BouPrimaryContainerDark,
    onPrimaryContainer = BouOnPrimaryContainerDark,
    secondary = BouSecondaryDark,
    onSecondary = BouOnSecondaryDark,
    background = BouBackgroundDark,
    onBackground = BouOnBackgroundDark,
    surface = BouSurfaceDark,
    onSurface = BouOnSurfaceDark,
    surfaceVariant = BouSurfaceVariantDark,
    onSurfaceVariant = BouOnSurfaceVariantDark
  )

private val LightColorScheme =
  lightColorScheme(
    primary = BouPrimary,
    onPrimary = BouOnPrimary,
    primaryContainer = BouPrimaryContainer,
    onPrimaryContainer = BouOnPrimaryContainer,
    secondary = BouSecondary,
    onSecondary = BouOnSecondary,
    secondaryContainer = BouSecondaryContainer,
    onSecondaryContainer = BouOnSecondaryContainer,
    tertiary = BouTertiary,
    onTertiary = BouOnTertiary,
    tertiaryContainer = BouTertiaryContainer,
    onTertiaryContainer = BouOnTertiaryContainer,
    background = BouBackgroundLight,
    onBackground = BouOnBackgroundLight,
    surface = BouSurfaceLight,
    onSurface = BouOnSurfaceLight,
    surfaceVariant = BouSurfaceVariant,
    onSurfaceVariant = BouOnSurfaceVariant
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Use our curated branding by default for high visual identity
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
