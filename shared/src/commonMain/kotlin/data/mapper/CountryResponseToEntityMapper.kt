package data.mapper

import cache.db.sqldelight.entity.CountryEntity
import data.remote.response.CountryResponse
import domain.mapper.base.Mapper

class CountryResponseToEntityMapper : Mapper<CountryResponse, CountryEntity> {

    override fun map(source: CountryResponse): CountryEntity =
        CountryEntity(
            name = source.name?.common.orEmpty(),
            imageUrl = source.flags?.png.orEmpty(),
        )
}