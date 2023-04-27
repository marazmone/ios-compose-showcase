package com.myapplication.preview.widget.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.tooling.preview.Preview
import presentation.ui.AppTheme
import presentation.ui.Colors
import presentation.ui.widget.common.PercentChangeWithPeriodWidget

@Preview(
    showBackground = true,
)
@Composable
fun PercentChangeWithPeriodWidget_UP_Preview() {
    AppTheme {
        PercentChangeWithPeriodWidget(
            period = "1D",
            percentChange = 10.33
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PercentChangeWithPeriodWidget_DOWN_Preview() {
    AppTheme {
        PercentChangeWithPeriodWidget(
            period = "1H",
            percentChange = -10.33
        )
    }
}