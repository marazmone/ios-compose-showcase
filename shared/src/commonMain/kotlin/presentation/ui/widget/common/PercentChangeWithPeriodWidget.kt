package presentation.ui.widget.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun PercentChangeWithPeriodWidget(
    period: String,
    percentChange: Double,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = period, style = MaterialTheme.typography.labelMedium)
        PercentChangeWidget(
            percentChange = percentChange,
            verticalAlignment = Alignment.CenterVertically,
        )
    }
}
