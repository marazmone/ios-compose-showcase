import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.MainScreen
import presentation.ui.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(MainScreen)
    }
}

expect fun getPlatformName(): String