package presentation.tabs.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
internal object FavoriteTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Favorite"
            val icon = painterResource("icons/ic_favorite_filled.png")

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }


    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(text = "Favorite")
        }
    }
}