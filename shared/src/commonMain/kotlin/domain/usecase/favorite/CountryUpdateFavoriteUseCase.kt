package domain.usecase.favorite

import domain.repository.CountryRepository

class CountryUpdateFavoriteUseCase(
    private val repository: CountryRepository,
) {

    suspend fun execute(name: String, isFavorite: Boolean) {
        repository.updateFavorite(name, isFavorite)
    }
}