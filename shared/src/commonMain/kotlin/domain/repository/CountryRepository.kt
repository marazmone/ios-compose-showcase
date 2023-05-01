package domain.repository

import domain.model.CountryModel

interface CountryRepository {

    suspend fun getAll(): List<CountryModel>
}