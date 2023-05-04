package domain.repository

import domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun getAll(): List<CountryModel>

    fun observeAll(): Flow<List<CountryModel>>
}