package presentation.ui.widget.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.model.currency.CurrencyListItem
import presentation.ui.Colors.Main.Background
import presentation.ui.Colors.Main.BackgroundSecond
import presentation.ui.widget.common.PercentChangeWidget
import presentation.util.AsyncImage

@Composable
fun CurrencyItemWidget(
    item: CurrencyListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(Background)
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            AsyncImage(
                imageUrl = item.imageUrl,
                loadingPlaceHolder = {
                    CircularProgressIndicator()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(BackgroundSecond)
                    )
                },
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
        }
        Column(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PercentChangeWidget(
                percentChange = item.percentChange24H,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                )
                Text(
                    text = item.mCap("MCap:"),
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                )
            }
        }
    }
}