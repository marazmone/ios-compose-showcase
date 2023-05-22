package presentation.tabs.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.statusBarsPadding
import domain.model.CountryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.tabs.list.ListContract.Effect
import presentation.tabs.list.ListContract.Effect.ShowAddFavoriteSnackBar
import presentation.tabs.list.ListContract.State
import presentation.tabs.list.composable.CountryItem
import presentation.tabs.list.detail.DetailScreen
import presentation.ui.AppTheme
import presentation.util.listen
import presentation.util.mainOrThrow

internal object ListTab : Tab, KoinComponent {

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
        val mainNavigator = LocalNavigator.mainOrThrow

        ListScreen(
            state = viewStateModel.state.value,
            effects = viewStateModel.effects,
            onOpenDetailScreen = { id ->
                mainNavigator.push(DetailScreen(id))
            },
            onChangeFavorite = { id, isFavorite ->
                viewStateModel.changeFavorite(id, isFavorite)
            },
            onSearchText = { query ->
                viewStateModel.search(query)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    state: State,
    effects: Flow<Effect>,
    onOpenDetailScreen: (id: String) -> Unit,
    onChangeFavorite: (id: String, isFavorite: Boolean) -> Unit,
    onSearchText: (query: String) -> Unit,
) {
    /**
     * values
     */
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    /**
     * effects
     */
    effects.listen { effect ->
        when (effect) {
            is ShowAddFavoriteSnackBar -> {
                snackScope.launch {
                    snackState.showSnackbar(effect.text)
                }
            }
        }
    }

    /**
     * layout
     */
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TextField(
                value = state.searchQuery,
                onValueChange = { query ->
                    onSearchText.invoke(query)
                },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.Search),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.Close),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                onSearchText.invoke("")
                            },
                    )
                },
                shape = CircleShape,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 16.dp),
            )
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(
                                alignment = Alignment.Center,
                            ),
                        )
                    }
                }

                state.isError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            text = state.errorMessage,
                            color = Color.Red,
                            modifier = Modifier.align(
                                alignment = Alignment.Center,
                            ),
                        )
                    }
                }

                state.list.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            text = "No data",
                            modifier = Modifier.align(
                                alignment = Alignment.Center,
                            ),
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(
                            items = state.list,
                            key = { item -> item.name },
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
}

@Composable
fun ListScreenPreview() {
    AppTheme {
        ListScreen(
            state = State(
                list = List(10) { CountryModel.mockItem }
            ),
            effects = emptyFlow(),
            onOpenDetailScreen = {},
            onChangeFavorite = { _, _ -> },
            onSearchText = {},
        )
    }
}