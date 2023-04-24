package presentation.detail

import presentation.base.BaseViewAction
import presentation.base.BaseViewEffect
import presentation.base.BaseViewState

class DetailContract {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = "",
        val detail: String = "",
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Loading : Action

        data class Error(val errorMessage: String) : Action

        data class Success(val detail: String) : Action
    }

    sealed interface Effect : BaseViewEffect
}