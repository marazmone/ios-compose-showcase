package com.myapplication.preview.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.tabs.favorite.FavoriteScreenPreview

@Preview(
    showBackground = true,
    name = "ListScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun FavoriteScreen_Preview() {
    FavoriteScreenPreview()
}