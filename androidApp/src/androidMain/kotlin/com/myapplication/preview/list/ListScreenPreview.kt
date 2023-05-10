package com.myapplication.preview.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.tabs.list.ListScreenPreview

@Preview(
    showBackground = true,
    name = "ListScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun ListScreen_Preview() {
    ListScreenPreview()
}