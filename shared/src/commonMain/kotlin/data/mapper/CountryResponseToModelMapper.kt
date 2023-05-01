package data.mapper

import data.remote.response.CountryResponse
import domain.mapper.base.Mapper
import domain.model.CountryModel

class CountryResponseToModelMapper : Mapper<CountryResponse, CountryModel> {

    override fun map(source: CountryResponse): CountryModel =
        CountryModel(
            name = source.name?.common.orEmpty(),
            imageUrl = source.flags?.png.orEmpty(),
        )
}