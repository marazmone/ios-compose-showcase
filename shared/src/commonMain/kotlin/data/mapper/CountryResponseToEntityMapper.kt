package data.mapper

import data.cache.entity.CountryEntity
import data.remote.response.CountryResponse
import domain.mapper.base.Mapper
import io.realm.kotlin.ext.toRealmList

class CountryResponseToEntityMapper : Mapper<CountryResponse, CountryEntity> {

    override fun map(source: CountryResponse): CountryEntity =
        CountryEntity().apply {
            name = source.name?.common.orEmpty()
            imageUrl = source.flags?.png.orEmpty()
            capitals = source.capitals.orEmpty().toRealmList()
            population = source.population ?: 0
            flag = source.flag.orEmpty()
        }

    companion object {
        const val NAMED = "CountryResponseToEntityMapper"
    }
}