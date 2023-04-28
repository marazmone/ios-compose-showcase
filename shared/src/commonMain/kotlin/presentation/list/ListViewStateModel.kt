package presentation.list

import domain.usecase.currency.CurrencyGetAllUseCase
import domain.usecase.currency.CurrencyObserveAllUseCase
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.list.ListContract.Action
import presentation.list.ListContract.Action.Error
import presentation.list.ListContract.Action.Loading
import presentation.list.ListContract.Action.Refresh
import presentation.list.ListContract.Action.Success
import presentation.list.ListContract.Effect
import presentation.list.ListContract.State

class ListViewStateModel(
    private val currencyGetAllUseCase: CurrencyGetAllUseCase,
    private val currencyObserveAllUseCase: CurrencyObserveAllUseCase,
) : BaseScreenStateModel<State, Action, Effect>() {

    init {
        getAllCurrency(false)
        observeAllCurrency()
    }

    override fun setInitialState(): State = State()

    override fun onReduceState(action: Action): State = when (action) {
        is Error -> currentState.copy(
            isLoading = false,
            isError = true,
            errorMessage = action.errorMessage,
            list = emptyList(),
            isRefresh = false,
        )

        is Loading -> currentState.copy(
            isLoading = true,
            isError = false,
            isRefresh = false,
        )

        is Success -> currentState.copy(
            isLoading = false,
            isError = false,
            list = action.list,
            isRefresh = false,
        )

        is Refresh -> {
            currentState.copy(
                isRefresh = true,
                isError = false,
            )
        }
    }

    fun getAllCurrency(withRefresh: Boolean = false) {
        launch {
            sendAction {
                if (withRefresh) Refresh else Loading
            }
            runCatching {
                currencyGetAllUseCase.execute()
            }.onFailure {
                sendAction { Error(it.message.orEmpty()) }
            }
        }
    }

    private fun observeAllCurrency() {
        launch {
            currencyObserveAllUseCase.execute().collect { list ->
                if (list.isNotEmpty()) sendAction { Success(list) }
            }
        }
    }
}