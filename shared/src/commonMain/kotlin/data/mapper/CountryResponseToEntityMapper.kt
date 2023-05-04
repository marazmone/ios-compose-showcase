package data.mapper

import data.cache.entity.CountryEntity
import data.remote.response.CountryResponse
import domain.mapper.base.Mapper

class CountryResponseToEntityMapper : Mapper<CountryResponse, CountryEntity> {

    override fun map(source: CountryResponse): CountryEntity =
        CountryEntity().apply {
            name = source.name?.common.orEmpty()
            imageUrl = source.flags?.png.orEmpty()
        }

    companion object {
        const val NAMED = "CountryResponseToEntityMapper"
    }
}