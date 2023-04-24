package presentation.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseScreenStateModel<STATE : BaseViewState, ACTION : BaseViewAction, EFFECT : BaseViewEffect> :
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate

    abstract fun setInitialState(): STATE

    private val initialState: STATE by lazy { setInitialState() }

    private val _state: MutableState<STATE> = mutableStateOf(initialState)
    val state: State<STATE> = _state

    private val _effects = Channel<EFFECT>()
    val effects = _effects.receiveAsFlow()

    protected var currentState by Delegates.observable(initialState) { _, _, new ->
        _state.value = new
    }

    protected fun sendEffect(builder: () -> EFFECT) {
        val effectValue = builder()
        launch { _effects.send(effectValue) }
    }

    protected fun sendAction(builder: () -> ACTION) {
        val actionValue = builder()
        currentState = onReduceState(actionValue)
    }

    protected abstract fun onReduceState(action: ACTION): STATE
}

interface BaseViewState

interface BaseViewAction

interface BaseViewEffect