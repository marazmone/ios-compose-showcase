package data.mapper

import cache.db.sqldelight.entity.CountryEntity
import domain.mapper.base.Mapper
import domain.model.CountryModel

class CountryEntityToModelMapper : Mapper<CountryEntity, CountryModel> {

    override fun map(source: CountryEntity): CountryModel =
        CountryModel(
            name = source.name,
            imageUrl = source.imageUrl,
        )
}