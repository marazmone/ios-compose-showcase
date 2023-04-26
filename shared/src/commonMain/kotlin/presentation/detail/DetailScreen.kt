package presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.detail.DetailContract.State

internal class DetailScreen(
    private val id: String,
) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewStateModel by inject<DetailViewStateModel>()

        DetailScreen(
            state = viewStateModel.state.value,
            onGetDetailInfo = {
                viewStateModel.getDetailInfo(id)
            },
            onClickPop = {
                navigator.pop()
            },
        )
    }
}

@Composable
fun DetailScreen(
    state: State,
    onGetDetailInfo: () -> Unit,
    onClickPop: () -> Unit,
) {
    LaunchedEffect(Unit) {
        onGetDetailInfo.invoke()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Exit",
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .clickable {
                    onClickPop.invoke()
                }
                .fillMaxWidth()
                .height(60.dp)
                .wrapContentHeight()
                .align(Alignment.TopCenter),
        )
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
                Text(
                    text = state.detail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .wrapContentHeight()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    color = Color.Green,
                )
            }
        }
    }
}