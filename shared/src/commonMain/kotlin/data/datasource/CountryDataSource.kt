package data.datasource

import data.remote.response.CountryResponse

interface CountryCacheDataSource {

    suspend fun saveOne()

    suspend fun saveAll()

    suspend fun getById()

    suspend fun getAll()

    fun observeAll()

    fun observeById()
}

interface CountryRemoteDataSource {

    suspend fun getAll(): List<CountryResponse>
}