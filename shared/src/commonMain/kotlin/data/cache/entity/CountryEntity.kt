package data.cache.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CountryEntity : RealmObject {

    @PrimaryKey
    var name: String = ""

    var imageUrl: String = ""

    var isFavorite: Boolean = false
}