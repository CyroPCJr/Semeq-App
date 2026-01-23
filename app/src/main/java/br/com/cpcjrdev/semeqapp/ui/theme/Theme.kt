package br.com.cpcjrdev.semeqapp.ui.theme

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

private val DarkColorScheme =
    darkColorScheme(
        primary = PrimaryPink,
        secondary = PurpleGrey80,
        tertiary = Pink80,
        background = PrimaryPink,
        surface = Color(0xFF1C1B1F),
        onSurface = Color.White,
        surfaceVariant = Color(0xFF49454F),
    )

private val LightColorScheme =
    lightColorScheme(
        primary = PrimaryPink,
        secondary = PurpleGrey40,
        tertiary = Pink40,
        background = PrimaryPink,
        surface = Color.White,
        onSurface = Color.Black,
        surfaceVariant = Gray94,
    )

@Composable
fun SemeqAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> {
                DarkColorScheme
            }

            else -> {
                LightColorScheme
            }
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
