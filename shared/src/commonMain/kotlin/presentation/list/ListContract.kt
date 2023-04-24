package presentation.list

import presentation.base.BaseViewAction
import presentation.base.BaseViewEffect
import presentation.base.BaseViewState

class ListContract {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = "",
        val list: List<String> = emptyList(),
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Loading : Action

        data class Error(val errorMessage: String) : Action

        data class Success(val list: List<String>) : Action
    }

    sealed interface Effect : BaseViewEffect {

        data class OpenDetailScreen(val id: String) : Effect
    }
}