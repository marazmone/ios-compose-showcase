package presentation.preview.list.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.tabs.list.composable.CountryItemPreview
import presentation.ui.AppTheme

@Preview(
    showBackground = true,
    name = "ListScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun CountryItem_Preview() {
    AppTheme {
        CountryItemPreview()
    }
}