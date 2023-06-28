package presentation.tabs.list.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.model.CountryModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.text.Strings
import presentation.ui.AppTheme
import presentation.util.AsyncImage

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CountryItem(
    model: CountryModel,
    onClickItem: (id: String) -> Unit,
    onClickFavorite: (id: String, isFavorite: Boolean) -> Unit,
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
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClickItem.invoke(model.name)
                },
        ) {
            Box {
                AsyncImage(
                    imageUrl = model.imageUrl,
                    loadingPlaceHolder = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
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
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(Color.Gray)
                                .align(Alignment.Center),
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds,
                )
                val painter = if (model.isFavorite) {
                    rememberVectorPainter(Icons.Default.Favorite)
                } else {
                    rememberVectorPainter(Icons.Default.FavoriteBorder)
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.5f))
                        .clip(CircleShape)
                        .clickable {
                            onClickFavorite.invoke(model.name, !model.isFavorite)
                        }
                        .padding(8.dp)
                        .align(Alignment.BottomEnd),
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Magenta),
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = model.name.plus(" ").plus(model.flag),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = Strings.format(id = "capital", model.capitals.ifEmpty { listOf("-") }.first()),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = Strings.format(id = "population", model.formattedPopulation),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CountryItemPreview() {
    AppTheme {
        CountryItem(
            model = CountryModel.mockItem,
            onClickItem = {},
            onClickFavorite = { _, _ -> },
        )
    }
}