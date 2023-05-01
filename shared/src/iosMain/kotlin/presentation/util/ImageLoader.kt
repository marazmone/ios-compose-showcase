package presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import presentation.util.ImageState.Error
import presentation.util.ImageState.Loading
import presentation.util.ImageState.Success

@Composable
actual fun AsyncImage(
    imageUrl: String,
    loadingPlaceHolder: @Composable (BoxScope.() -> Unit)?,
    errorPlaceHolder: @Composable (BoxScope.() -> Unit)?,
    contentDescription: String?,
    modifier: Modifier,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    coloFilter: ColorFilter?,
    filterQuality: FilterQuality,
) {
    val imageState by rememberImageState(imageUrl)
    Box(modifier = modifier) {
        when (val state = imageState) {
            Error -> errorPlaceHolder?.invoke(this)
            Loading -> loadingPlaceHolder?.invoke(this)
            is Success -> {
                Image(
                    bitmap = state.bitmap,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    alignment = alignment,
                    contentScale = contentScale,
                    alpha = alpha,
                    colorFilter = coloFilter,
                    filterQuality = filterQuality,
                )
            }
        }
    }
}

@Composable
fun rememberImageState(
    imageUrl: String,
) = produceState<ImageState>(initialValue = Loading) {
    runCatching {
        ImageUtils.getImageBitmapByUrl(imageUrl)
    }.onSuccess {
        value = Success(it)
    }.onFailure {
        value = Error
    }
}

sealed class ImageState {
    object Loading : ImageState()
    object Error : ImageState()
    data class Success(val bitmap: ImageBitmap) : ImageState()
}

object ImageUtils {

    private val client = HttpClient()

    private val inMemoryCache = mutableMapOf<String, ByteArray>()

    suspend fun getImageBitmapByUrl(url: String): ImageBitmap {
        val bytes = inMemoryCache.getOrPut(url) {
            client.get(url).readBytes()
        }
        val bitmap = withContext(Dispatchers.Default) {
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        }
        return bitmap
    }
}