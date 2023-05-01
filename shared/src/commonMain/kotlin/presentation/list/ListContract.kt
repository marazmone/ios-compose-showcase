package presentation.list

import domain.model.CountryModel
import presentation.base.BaseViewAction
import presentation.base.BaseViewEffect
import presentation.base.BaseViewState

class ListContract {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = "",
        val list: List<CountryModel> = emptyList(),
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Loading : Action

        data class Error(val errorMessage: String) : Action

        data class Success(val list: List<CountryModel>) : Action
    }

    sealed interface Effect : BaseViewEffect {

        data class OpenDetailScreen(val id: String) : Effect
    }
}