package domain.usecase.list

import domain.model.CountryModel
import domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryObserveAllCacheUseCase(
    private val repository: CountryRepository,
) {

    fun execute(): Flow<List<CountryModel>> = repository.observeAll()
}