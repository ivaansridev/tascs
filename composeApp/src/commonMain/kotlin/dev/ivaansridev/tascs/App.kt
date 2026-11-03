package dev.ivaansridev.tascs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.ivaansridev.tascs.ui.TascsApp
import dev.ivaansridev.tascs.ui.theme.TascsTheme

@Composable
fun App() {
    TascsTheme {
        // Paint the whole window with the theme background. Without this the
        // window surface is left unpainted and shows up as a black area on Linux.
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TascsApp()
        }
    }
}
