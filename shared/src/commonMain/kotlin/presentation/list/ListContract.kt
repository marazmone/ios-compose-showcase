package presentation.list

import domain.model.currency.CurrencyListItem
import presentation.base.BaseViewAction
import presentation.base.BaseViewEffect
import presentation.base.BaseViewState

class ListContract {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = "",
        val list: List<CurrencyListItem> = emptyList(),
        val isRefresh: Boolean = false,
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Loading : Action

        object Refresh : Action

        data class Error(val errorMessage: String) : Action

        data class Success(val list: List<CurrencyListItem>) : Action
    }

    sealed interface Effect : BaseViewEffect
}