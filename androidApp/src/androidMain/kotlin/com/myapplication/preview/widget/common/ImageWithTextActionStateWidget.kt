package com.myapplication.preview.widget.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.ui.AppTheme
import presentation.ui.widget.common.ImageWithTextActionStateWidget

@Preview(
    showBackground = true,
)
@Composable
private fun ImageWithTextActionStateWidget_Preview_Error() {
    AppTheme {
        ImageWithTextActionStateWidget(
            image = "image/im_error_state.png",
            text = "Something went wrong, please try again later",
        )
    }
}