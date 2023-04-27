package com.myapplication.preview.widget.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import presentation.ui.AppTheme
import presentation.ui.widget.common.PullRefreshWidget

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    showBackground = true,
)
@Composable
private fun PullRefreshWidget_Preview() {
    AppTheme {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = true,
            onRefresh = {},
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
        ) {
            PullRefreshWidget(
                state = pullRefreshState,
                refreshing = { true },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}