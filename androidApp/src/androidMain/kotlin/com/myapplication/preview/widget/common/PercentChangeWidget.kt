package com.myapplication.preview.widget.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.ui.AppTheme
import presentation.ui.widget.common.PercentChangeWidget

@Preview(
    showBackground = true,
    name = "UP",
)
@Composable
private fun PercentChangeWidget_UP_Preview() {
    AppTheme {
        PercentChangeWidget(percentChange = 10.33)
    }
}

@Preview(
    showBackground = true,
    name = "DOWN",
)
@Composable
fun PercentChangeWidget_DOWN_Preview() {
    AppTheme {
        PercentChangeWidget(percentChange = -10.22)
    }
}