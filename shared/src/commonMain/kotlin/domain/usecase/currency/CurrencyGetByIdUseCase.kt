package domain.usecase.currency

import domain.model.currency.CurrencyDetail
import domain.repository.currency.CurrencyRepository

class CurrencyGetByIdUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(id: String): CurrencyDetail? = repository.getCurrencyById(id)
}
