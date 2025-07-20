package dev.lkey.common.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF2AE881),
    surface = Color(0xFF1C1B1F),
    onSurfaceVariant = Color(0xFFBAC8C9),
    onSurface = Color(0xFF504D50),
    inverseOnSurface = Color(0xFFF3EDF7),
    outline = Color(0xFF6FCF97),
    surfaceTint = Color(0xFF2AE881),
    surfaceContainer = Color(0xFF2A2A2A),
    surfaceContainerLow = Color(0xFF1F1F1F),
    surfaceDim = Color(0xFF121212),
    primaryContainer = Color(0xFF2D463A),
    inverseSurface = Color(0xFFF3EDF7),
    secondaryContainer = Color(0xFF49454F),
    onSecondary = Color(0xFFD3FAE6),
    tertiaryContainer = Color(0xFFB53832),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2AE881),
    surface = Color(0xFFF3EDF7),
    onSurfaceVariant = Color(0xFFEFFAFF),
    onSurface = Color(0xFFFEF7FF),
    inverseOnSurface = Color(0xFF1D1B20),
    outline = Color(0xFFD3FAE6),
    surfaceTint = Color(0xFF29E881),
    surfaceContainer = Color(0xFF49454F),
    surfaceContainerLow = Color(0xFFD4FAE6),
    surfaceDim = Color(0xFFCAC4D0),
    primaryContainer = Color(0xFFECE6F0),
    inverseSurface = Color(0xFF3D3D3D),
    secondaryContainer = Color(0xFF7C7681),
    onSecondary = Color(0xFFE9E5EC),
    tertiaryContainer = Color(0xFFE46962),
)

@Composable
fun FinancilityTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDark -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}