package presentation.tabs.list.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.CountryModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.ui.AppTheme
import presentation.util.AsyncImage

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CountryItem(
    model: CountryModel,
    onClickItem: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.typography.titleLarge.color,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(60.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable {
                        onClickItem.invoke()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    imageUrl = model.imageUrl,
                    loadingPlaceHolder = {
                        Box(
                            modifier = Modifier
                                .size(44.dp),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center),
                            )
                        }
                    },
                    errorPlaceHolder = {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(Color.Gray)
                                .align(Alignment.Center),
                        )
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .heightIn(max = 44.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically),
                )
                Spacer(
                    Modifier
                        .width(12.dp),
                )
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f),
                )
                Image(
                    painter = painterResource("icons/ic_favorite_empty.png"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                )
            }
        }
    }
}

@Composable
fun CountryItemPreview() {
    AppTheme {
        CountryItem(
            model = CountryModel.mockItem,
            onClickItem = {},
        )
    }
}