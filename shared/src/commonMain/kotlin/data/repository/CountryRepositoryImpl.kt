package data.repository

import cache.db.sqldelight.entity.CountryEntity
import data.datasource.CountryCacheDataSource
import data.datasource.CountryRemoteDataSource
import data.remote.response.CountryResponse
import domain.mapper.base.Mapper
import domain.model.CountryModel
import domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryRepositoryImpl(
    private val cache: CountryCacheDataSource,
    private val remote: CountryRemoteDataSource,
    private val responseToEntityMapper: Mapper<CountryResponse, CountryEntity>,
    private val entityToModelMapper: Mapper<CountryEntity, CountryModel>,
) : CountryRepository {

    override suspend fun getAll(): List<CountryModel> {
        val remote = remote.getAll()
        val entities = responseToEntityMapper.listAsync(remote)
        cache.saveAll(entities)
        return entityToModelMapper.listAsync(entities)
    }

    override fun observeAll(): Flow<List<CountryModel>> =
        cache.observeAll().map { entityToModelMapper.listAsync(it) }
}