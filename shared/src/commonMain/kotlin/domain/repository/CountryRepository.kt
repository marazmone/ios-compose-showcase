package domain.repository

import domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun getAll(): List<CountryModel>

    suspend fun updateFavorite(name: String, isFavorite: Boolean)

    fun observeAll(): Flow<List<CountryModel>>

    fun observeAllFavorite(): Flow<List<CountryModel>>
}