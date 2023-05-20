package presentation.tabs.list

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
        val stableList: List<CountryModel> = emptyList(),
        val searchQuery: String = "",
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Loading : Action

        data class Error(val errorMessage: String) : Action

        data class Success(val list: List<CountryModel>) : Action

        data class Search(val query: String, val list: List<CountryModel>) : Action
    }

    sealed interface Effect : BaseViewEffect
}