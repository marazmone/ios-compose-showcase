package presentation.detail

import domain.usecase.detail.DetailGetUseCase
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.detail.DetailContract.Action
import presentation.detail.DetailContract.Action.Error
import presentation.detail.DetailContract.Action.Loading
import presentation.detail.DetailContract.Action.Success
import presentation.detail.DetailContract.Effect
import presentation.detail.DetailContract.State

class DetailViewStateModel(
    private val detailGetUseCase: DetailGetUseCase
) : BaseScreenStateModel<State, Action, Effect>() {

    override fun setInitialState(): State = State()

    override fun onReduceState(action: Action): State = when (action) {
        is Error -> currentState.copy(
            isLoading = false,
            isError = true,
            errorMessage = action.errorMessage,
            detail = "",
        )

        is Loading -> currentState.copy(
            isLoading = true,
            isError = false
        )

        is Success -> currentState.copy(
            isLoading = false,
            isError = false,
            detail = action.detail
        )
    }

    fun getDetailInfo(id: String) {
        launch {
            sendAction { Loading }
            val result = detailGetUseCase.execute(id)
            sendAction { Success(result) }
        }
    }
}