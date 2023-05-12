package data.datasource

import data.cache.entity.CountryEntity
import data.remote.response.CountryResponse
import kotlinx.coroutines.flow.Flow

interface CountryCacheDataSource {

    suspend fun save(entity: CountryEntity)

    suspend fun saveAll(entities: List<CountryEntity>)

    suspend fun getAll(): List<CountryEntity>

    suspend fun updateFavorite(name: String, isFavorite: Boolean)

    fun observeAll(): Flow<List<CountryEntity>>

    fun observeAllFavorite(): Flow<List<CountryEntity>>
}

interface CountryRemoteDataSource {

    suspend fun getAll(): List<CountryResponse>
}