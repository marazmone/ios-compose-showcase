package data.datasource

import data.cache.entity.CountryEntity
import data.util.insertOrUpdate
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryCacheDataSourceImpl(
    private val realm: Realm,
) : CountryCacheDataSource {


    override suspend fun save(entity: CountryEntity) {
        realm.write {
            insertOrUpdate(entity)
        }
    }

    override suspend fun saveAll(entities: List<CountryEntity>) {
        realm.write {
            entities.forEach(::insertOrUpdate)
        }
    }

    override suspend fun getAll(): List<CountryEntity> =
        realm.query<CountryEntity>().find()

    override fun observeAll(): Flow<List<CountryEntity>> =
        realm.query<CountryEntity>().find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list
            }
        }
}