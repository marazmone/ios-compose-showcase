package data.datasource

import data.remote.RemoteConst.Url.GetAll
import data.remote.response.CountryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CountryRemoteDataSourceImpl(
    private val apiClient: HttpClient,
) : CountryRemoteDataSource {

    override suspend fun getAll(): List<CountryResponse> {
        val request = apiClient.get(GetAll)
        return request.body()
    }
}