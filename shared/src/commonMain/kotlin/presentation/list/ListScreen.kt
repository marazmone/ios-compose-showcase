package presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.CountryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.detail.DetailScreen
import presentation.list.ListContract.Effect
import presentation.list.ListContract.Effect.OpenDetailScreen
import presentation.list.ListContract.State
import presentation.list.composable.CountryItem
import presentation.ui.AppTheme
import presentation.util.listen

internal class ListScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewStateModel by inject<ListViewStateModel>()

        ListScreen(
            state = viewStateModel.state.value,
            effects = viewStateModel.effects,
            onNavigateToDetail = { id ->
                navigator.push(DetailScreen(id))
            },
            onOpenDetailScreen = { id ->
                viewStateModel.openDetailScreen(id)
            },
        )
    }
}

@Composable
fun ListScreen(
    state: State,
    effects: Flow<Effect>,
    onNavigateToDetail: (id: String) -> Unit,
    onOpenDetailScreen: (id: String) -> Unit,
) {
    effects.listen { effect ->
        when (effect) {
            is OpenDetailScreen -> {
                onNavigateToDetail.invoke(effect.id)
            }
        }
    }

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

            else -> {
                LazyColumn {
                    items(state.list) { item ->
                        CountryItem(
                            model = item,
                            onClickItem = {
                                onOpenDetailScreen.invoke(item.name)
                            }
                        )
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
            onNavigateToDetail = {},
            onOpenDetailScreen = {},
        )
    }
}