package domain.repository.currency

import domain.model.currency.CurrencyDetail
import domain.model.currency.CurrencyListItem
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getAll(): List<CurrencyListItem>

    suspend fun getCurrencyById(id: String): CurrencyDetail?

    suspend fun updateByIds(ids: List<String>): List<CurrencyListItem>

    fun observeAll(): Flow<List<CurrencyListItem>>
}
