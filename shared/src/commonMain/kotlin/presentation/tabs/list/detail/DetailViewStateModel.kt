package presentation.tabs.list.detail

import domain.usecase.detail.DetailGetUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import presentation.base.BaseScreenStateModel
import presentation.tabs.list.detail.DetailContract.Action
import presentation.tabs.list.detail.DetailContract.Action.Error
import presentation.tabs.list.detail.DetailContract.Action.Loading
import presentation.tabs.list.detail.DetailContract.Action.Success
import presentation.tabs.list.detail.DetailContract.Effect
import presentation.tabs.list.detail.DetailContract.State

class DetailViewStateModel(
    private val detailGetUseCase: DetailGetUseCase
) : BaseScreenStateModel<State, Action, Effect>() {

    init {
        Napier.d("DetailViewStateModel init ${this.hashCode()}")
    }

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