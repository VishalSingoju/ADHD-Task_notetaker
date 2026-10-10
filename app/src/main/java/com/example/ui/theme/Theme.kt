package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
  primary = PrimaryColor,
  onPrimary = OnPrimaryColor,
  primaryContainer = PrimaryContainer,
  onPrimaryContainer = OnPrimaryContainer,
  inversePrimary = AccentLilac,
  secondary = SecondaryColor,
  onSecondary = OnSecondaryColor,
  secondaryContainer = SecondaryContainer,
  onSecondaryContainer = OnSecondaryContainer,
  tertiary = TertiaryColor,
  onTertiary = OnTertiaryColor,
  tertiaryContainer = TertiaryContainer,
  onTertiaryContainer = OnTertiaryContainer,
  background = SurfaceBackground,
  onBackground = OnSurface,
  surface = SurfaceBackground,
  onSurface = OnSurface,
  surfaceVariant = SurfaceContainerHighest,
  onSurfaceVariant = OnSurfaceVariant,
  surfaceContainerLowest = SurfaceContainerLowest,
  surfaceContainerLow = SurfaceContainerLow,
  surfaceContainer = SurfaceContainer,
  surfaceContainerHigh = SurfaceContainerHigh,
  surfaceContainerHighest = SurfaceContainerHighest,
  outline = OutlineColor,
  outlineVariant = OutlineVariant,
  error = ErrorColor,
  onError = OnErrorColor,
  errorContainer = ErrorContainer,
  onErrorContainer = OnErrorContainer
)

private val DarkColorScheme = darkColorScheme(
  primary = AccentLilac,
  onPrimary = OnPrimaryColor,
  primaryContainer = PrimaryContainer,
  onPrimaryContainer = OnPrimaryContainer,
  secondary = AccentSage,
  onSecondary = OnSecondaryColor,
  secondaryContainer = SecondaryContainer,
  onSecondaryContainer = OnSecondaryContainer,
  tertiary = AccentPeach,
  onTertiary = OnTertiaryColor,
  background = SurfaceBackground,
  onBackground = OnSurface,
  surface = SurfaceBackground,
  onSurface = OnSurface,
  surfaceVariant = SurfaceContainerHighest,
  onSurfaceVariant = OnSurfaceVariant
)

@Composable
fun SparkQuestTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      if (window != null) {
        window.statusBarColor = SurfaceBackground.toArgb()
        window.navigationBarColor = SurfaceBackground.toArgb()
        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true
      }
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}
