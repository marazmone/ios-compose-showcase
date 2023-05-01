package com.myapplication.preview.list.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.list.composable.CountryItemPreview

@Preview(
    showBackground = true,
    name = "ListScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun CountryItem_Preview() {
    CountryItemPreview()
}