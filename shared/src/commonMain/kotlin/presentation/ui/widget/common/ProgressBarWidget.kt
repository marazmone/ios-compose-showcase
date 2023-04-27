package presentation.ui.widget.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ProgressBarWidget(
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int,
    modifier: Modifier = Modifier,
) {
    Box {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .width(width)
        )
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )
    }
}
