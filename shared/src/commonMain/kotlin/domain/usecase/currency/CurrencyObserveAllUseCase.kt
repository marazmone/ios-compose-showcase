package domain.usecase.currency

import domain.model.currency.CurrencyListItem
import domain.repository.currency.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyObserveAllUseCase(
    private val repository: CurrencyRepository,
) {

    fun execute(): Flow<List<CurrencyListItem>> = repository.observeAll()
}
