package com.myapplication.preview.detail

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.detail.DetailContract.State
import presentation.detail.DetailScreen

@Preview(
    showBackground = true,
    name = "DetailScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun DetailScreenPreview() {
    MaterialTheme {
        DetailScreen(
            state = State(
                detail = "id=1"
            ),
            onClickPop = {},
            onGetDetailInfo = {},
        )
    }
}