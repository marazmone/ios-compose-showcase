package presentation.tabs.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.statusBars
import domain.model.CountryModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.tabs.list.ListContract.State
import presentation.tabs.list.composable.CountryItem
import presentation.tabs.list.detail.DetailScreen
import presentation.ui.AppTheme

internal class ListTab(
    private val mainNavigator: Navigator,
) : Tab, KoinComponent {

    override val options: TabOptions
        @Composable
        get() {
            val title = "List"
            val icon = rememberVectorPainter(Icons.Default.List)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewStateModel by inject<ListViewStateModel>()

        ListScreen(
            state = viewStateModel.state.value,
            onOpenDetailScreen = { id ->
                mainNavigator.push(DetailScreen(id))
            },
            onChangeFavorite = { id, isFavorite ->
                viewStateModel.changeFavorite(id, isFavorite)
            }
        )
    }
}

@Composable
fun ListScreen(
    state: State,
    onOpenDetailScreen: (id: String) -> Unit,
    onChangeFavorite: (id: String, isFavorite: Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            state.isError -> {
                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            state.list.isEmpty() -> {
                Text(
                    text = "No data",
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = WindowInsets.statusBars.add(WindowInsets.navigationBars).asPaddingValues(),
                ) {
                    items(
                        state.list,
                    ) { item ->
                        CountryItem(
                            model = item,
                            onClickItem = { id ->
                                onOpenDetailScreen.invoke(id)
                            },
                            onClickFavorite = { id, isFavorite ->
                                onChangeFavorite.invoke(id, isFavorite)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ListScreenPreview() {
    AppTheme {
        ListScreen(
            state = State(
                list = List(10) { CountryModel.mockItem }
            ),
            onOpenDetailScreen = {},
            onChangeFavorite = { _, _ -> }
        )
    }
}