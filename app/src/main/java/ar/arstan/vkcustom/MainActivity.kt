package ar.arstan.vkcustom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import ar.arstan.vkcustom.ui.theme.MainScreen
import ar.arstan.vkcustom.ui.theme.VkCustomTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkCustomTheme {
                MainScreen(viewModel)
            }
        }
    }
}