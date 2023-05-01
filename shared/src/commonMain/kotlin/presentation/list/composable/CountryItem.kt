package presentation.list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.CountryModel
import presentation.ui.AppTheme
import presentation.util.AsyncImage

@Composable
fun CountryItem(
    model: CountryModel,
    onClickItem: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                onClickItem.invoke()
            }
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            imageUrl = model.imageUrl,
            loadingPlaceHolder = {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.Center),
                ) {
                    CircularProgressIndicator()
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
                .wrapContentHeight(),
        )
        Spacer(
            Modifier
                .width(12.dp),
        )
        Text(
            text = model.name,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
        )
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