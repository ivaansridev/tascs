package dev.ivaansridev.tascs.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Dark Material 3 palette built around a yellow primary colour.
 */
private val YellowDarkColors =
    darkColorScheme(
        primary = Color(0xFFFFD54F),
        onPrimary = Color(0xFF3B2F00),
        primaryContainer = Color(0xFF564600),
        onPrimaryContainer = Color(0xFFFFE082),
        secondary = Color(0xFFD5C58A),
        onSecondary = Color(0xFF3A3000),
        secondaryContainer = Color(0xFF514700),
        onSecondaryContainer = Color(0xFFF2E3A3),
        tertiary = Color(0xFFB9CC8F),
        onTertiary = Color(0xFF253508),
        tertiaryContainer = Color(0xFF3B4C1C),
        onTertiaryContainer = Color(0xFFD5E8A8),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF14130C),
        onBackground = Color(0xFFE9E2D3),
        surface = Color(0xFF14130C),
        onSurface = Color(0xFFE9E2D3),
        surfaceVariant = Color(0xFF4B4736),
        onSurfaceVariant = Color(0xFFCDC6B2),
        surfaceDim = Color(0xFF14130C),
        surfaceBright = Color(0xFF3B3927),
        surfaceContainerLowest = Color(0xFF0F0E07),
        surfaceContainerLow = Color(0xFF1C1B12),
        surfaceContainer = Color(0xFF201F16),
        surfaceContainerHigh = Color(0xFF2B2A1E),
        surfaceContainerHighest = Color(0xFF363527),
        outline = Color(0xFF97907D),
        outlineVariant = Color(0xFF4B4736),
        inverseSurface = Color(0xFFE9E2D3),
        inverseOnSurface = Color(0xFF32301F),
        inversePrimary = Color(0xFF715C00),
        scrim = Color(0xFF000000),
    )

@Composable
fun TascsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = YellowDarkColors,
        content = content,
    )
}
