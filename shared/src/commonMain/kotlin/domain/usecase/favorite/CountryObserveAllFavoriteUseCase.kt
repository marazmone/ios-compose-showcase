package domain.usecase.favorite

import domain.model.CountryModel
import domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryObserveAllFavoriteUseCase(
    private val repository: CountryRepository,
) {

    fun execute(): Flow<List<CountryModel>> = repository.observeAllFavorite()
}