import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.tabs.list.ListTab
import presentation.ui.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(ListTab)
    }
}

expect fun getPlatformName(): String