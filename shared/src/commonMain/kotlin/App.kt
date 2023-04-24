import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.list.ListScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(ListScreen())
    }
}

expect fun getPlatformName(): String