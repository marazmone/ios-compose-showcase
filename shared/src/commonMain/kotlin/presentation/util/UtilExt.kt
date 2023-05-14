package presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import cafe.adriel.voyager.navigator.Navigator
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

val ProvidableCompositionLocal<Navigator?>.mainOrThrow: Navigator
    @Composable
    get() {
        return current?.parent ?: throw IllegalStateException("No parent navigator found")
    }