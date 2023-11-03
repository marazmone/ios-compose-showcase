package presentation.preview.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.tabs.list.detail.DetailContract.State
import presentation.tabs.list.detail.DetailScreen
import presentation.ui.AppTheme

@Preview(
    showBackground = true,
    name = "DetailScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun DetailScreenPreview() {
    AppTheme {
        DetailScreen(
            state = State(
                detail = "id=1"
            ),
            onClickPop = {},
            onGetDetailInfo = {},
        )
    }
}