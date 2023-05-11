package presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.moriatsushi.insetsx.navigationBars
import presentation.tabs.favorite.FavoriteTab
import presentation.tabs.list.ListTab

@OptIn(ExperimentalMaterial3Api::class)
internal object MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        TabNavigator(ListTab(navigator)) {
            Scaffold(
                content = {
                    Box(
                        modifier = Modifier
                            .padding(bottom = it.calculateBottomPadding()),
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    Column {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,
                        ) {
                            TabNavigationItem(ListTab(navigator))
                            TabNavigationItem(FavoriteTab)
                        }
                        Spacer(
                            Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(WindowInsets.navigationBars.asPaddingValues())
                                .fillMaxWidth()
                        )
                    }

                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
            selectedContentColor = MaterialTheme.colorScheme.onSecondary,
        )
    }
}