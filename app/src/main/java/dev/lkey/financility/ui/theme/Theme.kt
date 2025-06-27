package dev.lkey.financility.ui.theme

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
    surface = Color(0xFFF3EDF7),
    onSurfaceVariant = Color(0xFFEFFAFF),
    onSurface = Color(0xFFFEF7FF),
    inverseOnSurface = Color(0xFF1D1B20),
    outline = Color(0xFFD4FAE6),
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
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}