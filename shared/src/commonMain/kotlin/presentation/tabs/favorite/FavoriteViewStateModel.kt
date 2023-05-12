package presentation.tabs.favorite

import domain.usecase.favorite.CountryObserveAllFavoriteUseCase
import domain.usecase.favorite.CountryUpdateFavoriteUseCase
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.tabs.favorite.FavoriteContract.Action
import presentation.tabs.favorite.FavoriteContract.Effect
import presentation.tabs.favorite.FavoriteContract.State

class FavoriteViewStateModel(
    private val countryObserveAllFavoriteUseCase: CountryObserveAllFavoriteUseCase,
    private val countryUpdateFavoriteUseCase: CountryUpdateFavoriteUseCase,
) : BaseScreenStateModel<State, Action, Effect>() {

    init {
        launch {
            countryObserveAllFavoriteUseCase.execute().collect {
                sendAction { Action.Success(it) }
            }
        }
    }

    override fun setInitialState(): State = State()

    override fun onReduceState(action: Action): State = when (action) {
        is Action.Success -> currentState.copy(
            list = action.list
        )
    }

    fun changeFavorite(name: String, isFavorite: Boolean) {
        launch {
            countryUpdateFavoriteUseCase.execute(name, isFavorite)
        }
    }
}