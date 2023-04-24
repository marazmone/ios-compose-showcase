package presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import presentation.base.BaseViewEffect

@Composable
fun <T> Flow<T>.listen(collector: (effect: T) -> Unit) where T : BaseViewEffect {
    LaunchedEffect(Unit) {
        this@listen.onEach { effect -> collector(effect) }.collect()
    }
}