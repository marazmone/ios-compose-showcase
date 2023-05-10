package presentation.tabs.list

import domain.usecase.list.CountryGetAllRemoteUseCase
import domain.usecase.list.CountryObserveAllCacheUseCase
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.tabs.list.ListContract.Action
import presentation.tabs.list.ListContract.Action.Error
import presentation.tabs.list.ListContract.Action.Loading
import presentation.tabs.list.ListContract.Action.Success
import presentation.tabs.list.ListContract.Effect
import presentation.tabs.list.ListContract.Effect.OpenDetailScreen
import presentation.tabs.list.ListContract.State

class ListViewStateModel(
    private val countryGetAllRemoteUseCase: CountryGetAllRemoteUseCase,
    private val countryObserveAllCacheUseCase: CountryObserveAllCacheUseCase,
) : BaseScreenStateModel<State, Action, Effect>() {

    init {
        getList()
        observeList()
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

    fun openDetailScreen(id: String) {
        sendEffect { OpenDetailScreen(id) }
    }

    private fun getList() {
        launch {
            sendAction { Loading }
            runCatching {
                countryGetAllRemoteUseCase.execute()
            }.onFailure {
                sendAction { Error(it.message.orEmpty()) }
            }
        }
    }

    private fun observeList() {
        launch {
            countryObserveAllCacheUseCase.execute().collect { list ->
                if (list.isNotEmpty()) sendAction { Success(list) }
            }
        }
    }
}