package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.detail.DetailScreen
import presentation.list.ListContract.State
import presentation.ui.widget.common.ImageWithTextActionStateWidget
import presentation.ui.widget.common.PullRefreshWidget
import presentation.ui.widget.currency.CurrencyItemWidget

internal class ListScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewStateModel by inject<ListViewStateModel>()

        ListScreen(
            state = viewStateModel.state.value,
            onRefresh = {
                viewStateModel.getAllCurrency(it)
            },
            onOpenDetailScreenAction = { id ->
                navigator.push(DetailScreen(id))
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    state: State,
    onRefresh: (Boolean) -> Unit,
    onOpenDetailScreenAction: (currencyId: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(state.isRefresh, { onRefresh.invoke(true) })
    val lazyListState = rememberLazyListState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.isError -> {
                ImageWithTextActionStateWidget(
                    image = "image/im_error_state.png",
                    text = state.errorMessage
                ) {
                    onRefresh.invoke(false)
                }
            }

            state.list.isNotEmpty() -> {
                LazyColumn(
                    state = lazyListState,
                ) {
                    items(state.list) { item ->
                        CurrencyItemWidget(
                            item = item,
                            modifier = Modifier.clickable {
                                onOpenDetailScreenAction.invoke(item.id)
                            }
                        )
                    }
                }
            }
        }
        PullRefreshWidget(
            state = pullRefreshState,
            refreshing = { state.isRefresh },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}