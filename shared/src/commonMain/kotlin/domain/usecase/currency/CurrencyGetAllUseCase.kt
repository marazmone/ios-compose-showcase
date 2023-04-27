package domain.usecase.currency

import domain.model.currency.CurrencyListItem
import domain.repository.currency.CurrencyRepository

class CurrencyGetAllUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(): List<CurrencyListItem> = repository.getAll()
}
