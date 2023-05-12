package domain.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CountryModel(
    val name: String,
    val imageUrl: String,
    private val _isFavorite: Boolean,
) {

    var isFavorite by mutableStateOf(_isFavorite)

    companion object {

        val mockItem: CountryModel
            get() = CountryModel(
                name = "Ukraine",
                imageUrl = "",
                _isFavorite = false,
            )
    }
}