package domain.usecase.currency

import domain.model.currency.CurrencyListItem
import domain.repository.currency.CurrencyRepository

class CurrencyUpdateByIdsUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(ids: List<String>): List<CurrencyListItem> = repository.updateByIds(ids)
}
