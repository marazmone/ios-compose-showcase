package presentation.ui.widget.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import presentation.ui.Colors.Main

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshWidget(
    state: PullRefreshState,
    refreshing: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    PullRefreshIndicator(
        refreshing = refreshing.invoke(),
        state = state,
        backgroundColor = Main.BackgroundSecond,
        contentColor = Color.White,
        scale = true,
        modifier = modifier,
    )
}
