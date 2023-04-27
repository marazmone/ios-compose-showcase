@file:OptIn(ExperimentalResourceApi::class)

package presentation.ui.widget.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import data.utils.amountWithCurrency
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.ui.Colors.Rate.Down
import presentation.ui.Colors.Rate.Up

private const val RateDownAngle = 180f
private const val RateTopAngle = 0f

@Composable
fun PercentChangeWidget(
    percentChange: Double,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {
    val rateColor = if (percentChange < 0f) Down else Up
    val rateRotate = if (percentChange < 0f) RateDownAngle else RateTopAngle
    val formattedText = percentChange.amountWithCurrency("%")
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier
                .size(8.dp)
                .rotate(rateRotate),
            tint = rateColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = formattedText,
            style = textStyle.copy(color = rateColor)
        )
    }
}
