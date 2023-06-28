package domain.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CountryModel(
    val name: String,
    val imageUrl: String,
    val capitals: List<String>,
    val flag: String,
    private val _population: Int,
    private val _isFavorite: Boolean,
) {

    var isFavorite by mutableStateOf(_isFavorite)

    val formattedPopulation: String
        get() = _population.toString().reversed().chunked(3).joinToString(" ").reversed()

    companion object {

        val mockItem: CountryModel
            get() = CountryModel(
                name = "Ukraine",
                imageUrl = "",
                _isFavorite = false,
                flag = "\uD83C\uDDEA\uD83C\uDDF8",
                capitals = listOf("Kyiv"),
                _population = 41_000_000,
            )
    }
}