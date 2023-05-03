package data.datasource

import cache.db.sqldelight.entity.CountryEntity
import data.remote.response.CountryResponse
import kotlinx.coroutines.flow.Flow

interface CountryCacheDataSource {

    suspend fun save(entity: CountryEntity)

    suspend fun saveAll(entities: List<CountryEntity>)

    suspend fun getAll(): List<CountryEntity>

    fun observeAll(): Flow<List<CountryEntity>>
}

interface CountryRemoteDataSource {

    suspend fun getAll(): List<CountryResponse>
}