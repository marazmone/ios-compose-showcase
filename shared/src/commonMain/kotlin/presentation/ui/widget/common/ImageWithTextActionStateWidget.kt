package presentation.ui.widget.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.ui.Colors

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageWithTextActionStateWidget(
    image: String,
    text: String,
    onActionRepeat: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp),
            textAlign = TextAlign.Center,
        )
        onActionRepeat?.also { action ->
            Button(
                onClick = action,
                colors = Colors.Button.DefaultButton,
            ) {
                Text(
                    text = "Repeat",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
