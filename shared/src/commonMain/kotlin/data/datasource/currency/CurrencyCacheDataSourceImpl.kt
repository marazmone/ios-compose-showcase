package data.datasource.currency

import app.cash.sqldelight.coroutines.asFlow
import cache.db.Database
import cache.db.sqldelight.entity.currency.CurrencyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyCacheDataSourceImpl(
    private val database: Database,
) : CurrencyCacheDataSource {

    private val currencyEntity by lazy { database.currencyEntityQueries }

    override suspend fun save(entity: CurrencyEntity) {
        currencyEntity.insertEntity(entity)
    }

    override suspend fun save(entities: List<CurrencyEntity>) {
        entities.forEach { save(it) }
    }

    override suspend fun getById(id: String): CurrencyEntity? =
        currencyEntity.selectById(id).executeAsOneOrNull()

    override suspend fun getAll(): List<CurrencyEntity> =
        currencyEntity.selectAll().executeAsList()

    override fun observeAll(): Flow<List<CurrencyEntity>> =
        currencyEntity.selectAll().asFlow().map { it.executeAsList() }
}