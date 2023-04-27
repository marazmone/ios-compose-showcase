package com.myapplication.preview.widget.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import presentation.ui.AppTheme
import presentation.ui.Colors.Gradient
import presentation.ui.widget.common.ProgressBarWidget

@Preview(
    showBackground = true,
)
@Composable
private fun ProgressBarWidget_Preview() {
    AppTheme {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        ProgressBarWidget(
            width = screenWidth,
            backgroundColor = Color.White,
            foregroundColor = Brush.horizontalGradient(listOf(Gradient.LightStart, Gradient.LightEnd)),
            percent = 80,
            modifier = Modifier
                .clip(shape = CircleShape)
                .height(12.dp),
        )
    }
}