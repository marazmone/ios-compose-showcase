package data.repository

import cache.db.sqldelight.entity.currency.CurrencyEntity
import data.datasource.currency.CurrencyCacheDataSource
import data.datasource.currency.CurrencyRemoteDataSource
import data.remote.response.CurrencyResponse
import domain.mapper.base.Mapper
import domain.model.currency.CurrencyDetail
import domain.model.currency.CurrencyListItem
import domain.repository.currency.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val remote: CurrencyRemoteDataSource,
    private val cache: CurrencyCacheDataSource,
    private val responseToEntityMapper: Mapper<CurrencyResponse, CurrencyEntity>,
    private val entityToListItemMapper: Mapper<CurrencyEntity, CurrencyListItem>,
    private val entityToDetailMapper: Mapper<CurrencyEntity, CurrencyDetail>,
) : CurrencyRepository {

    override suspend fun getAll(): List<CurrencyListItem> {
        val remote = remote.getAll()
        val entities = responseToEntityMapper.listAsync(remote)
        cache.save(entities)
        return entityToListItemMapper.listAsync(entities)
    }

    override suspend fun getCurrencyById(id: String): CurrencyDetail? =
        cache.getById(id)?.let { entityToDetailMapper.mapAsync(it) }

    override suspend fun updateByIds(ids: List<String>): List<CurrencyListItem> {
        val remote = remote.getAll(ids = ids.joinToString(","))
        val entities = responseToEntityMapper.listAsync(remote)
        cache.save(entities)
        return entityToListItemMapper.listAsync(entities)
    }

    override fun observeAll(): Flow<List<CurrencyListItem>> =
        cache.observeAll().map { entityToListItemMapper.listAsync(it) }
}
