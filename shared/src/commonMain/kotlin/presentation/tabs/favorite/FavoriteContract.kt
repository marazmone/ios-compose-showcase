package presentation.tabs.favorite

import domain.model.CountryModel
import presentation.base.BaseViewAction
import presentation.base.BaseViewEffect
import presentation.base.BaseViewState

class FavoriteContract {

    data class State(
        val list: List<CountryModel> = emptyList(),
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        data class Success(val list: List<CountryModel>) : Action
    }

    sealed interface Effect : BaseViewEffect
}