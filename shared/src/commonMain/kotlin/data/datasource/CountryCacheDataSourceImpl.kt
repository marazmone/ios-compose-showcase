package data.datasource

import app.cash.sqldelight.coroutines.asFlow
import cache.db.Database
import cache.db.sqldelight.entity.CountryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryCacheDataSourceImpl(
    private val database: Database,
) : CountryCacheDataSource {

    private val countryEntityQueries by lazy { database.countryEntityQueries }

    override suspend fun save(entity: CountryEntity) =
        countryEntityQueries.insertEntity(entity)

    override suspend fun saveAll(entities: List<CountryEntity>) =
        entities.forEach { save(it) }

    override suspend fun getAll(): List<CountryEntity> =
        countryEntityQueries.selectAll().executeAsList()

    override fun observeAll(): Flow<List<CountryEntity>> =
        countryEntityQueries.selectAll().asFlow().map { it.executeAsList() }
}