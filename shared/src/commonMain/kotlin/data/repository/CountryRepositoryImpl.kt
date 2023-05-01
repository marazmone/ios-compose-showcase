package data.repository

import data.datasource.CountryCacheDataSource
import data.datasource.CountryRemoteDataSource
import data.mapper.CountryResponseToModelMapper
import data.remote.response.CountryResponse
import domain.mapper.base.Mapper
import domain.model.CountryModel
import domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val cache: CountryCacheDataSource,
    private val remote: CountryRemoteDataSource,
    private val responseToModelMapper: Mapper<CountryResponse, CountryModel>,
) : CountryRepository {

    override suspend fun getAll(): List<CountryModel> {
        val remote = remote.getAll()
        return responseToModelMapper.list(remote)
    }
}