package data.datasource.currency

import cache.db.sqldelight.entity.currency.CurrencyEntity
import data.remote.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyCacheDataSource {

    suspend fun save(entity: CurrencyEntity)

    suspend fun save(entities: List<CurrencyEntity>)

    suspend fun getById(id: String): CurrencyEntity?

    suspend fun getAll(): List<CurrencyEntity>

    fun observeAll(): Flow<List<CurrencyEntity>>
}

interface CurrencyRemoteDataSource {

    suspend fun getAll(
        vsCurrency: String = "USD",
        priceChangePercentage: String = "1h,7d",
        ids: String? = null,
    ): List<CurrencyResponse>
}