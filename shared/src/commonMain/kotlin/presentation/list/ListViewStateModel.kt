package presentation.list

import domain.usecase.list.CountryGetAllRemoteUseCase
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.list.ListContract.Action
import presentation.list.ListContract.Action.Error
import presentation.list.ListContract.Action.Loading
import presentation.list.ListContract.Action.Success
import presentation.list.ListContract.Effect
import presentation.list.ListContract.Effect.OpenDetailScreen
import presentation.list.ListContract.State

class ListViewStateModel(
    private val countryGetAllRemoteUseCase: CountryGetAllRemoteUseCase,
) : BaseScreenStateModel<State, Action, Effect>() {

    init {
        getList()
    }

    override fun setInitialState(): State = State()

    override fun onReduceState(action: Action): State = when (action) {
        is Error -> currentState.copy(
            isLoading = false,
            isError = true,
            errorMessage = action.errorMessage,
            list = emptyList(),
        )

        is Loading -> currentState.copy(
            isLoading = true,
            isError = false
        )

        is Success -> currentState.copy(
            isLoading = false,
            isError = false,
            list = action.list
        )
    }

    private fun getList() {
        launch {
            sendAction { Loading }
            runCatching {
                countryGetAllRemoteUseCase.execute()
            }.onSuccess {
                sendAction { Success(it) }
            }.onFailure {
                sendAction { Error(it.message.orEmpty()) }
            }
        }
    }

    fun openDetailScreen(id: String) {
        sendEffect { OpenDetailScreen(id) }
    }
}