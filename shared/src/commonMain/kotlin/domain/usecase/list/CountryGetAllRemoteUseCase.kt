package domain.usecase.list

import domain.model.CountryModel
import domain.repository.CountryRepository

class CountryGetAllRemoteUseCase(
    private val repository: CountryRepository,
) {

    suspend fun execute(): List<CountryModel> = repository.getAll()
}