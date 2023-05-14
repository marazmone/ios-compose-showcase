package presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import presentation.tabs.TabsScreen

internal object MainScreen : Screen {

    @Composable
    override fun Content() {
        Navigator(TabsScreen)
    }
}