package com.myapplication.preview.list

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.emptyFlow
import presentation.list.ListContract.State
import presentation.list.ListScreen

@Preview(
    showBackground = true,
    name = "ListScreen",
    device = "id:pixel_5",
    showSystemUi = true,
)
@Composable
fun ListScreenPreview() {
    MaterialTheme {
        ListScreen(
            state = State(
                list = List(10) { "Item $it" }
            ),
            effects = emptyFlow(),
            onNavigateToDetail = {},
            onOpenDetailScreen = {},
        )
    }
}