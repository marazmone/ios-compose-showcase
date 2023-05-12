package domain.model

data class CountryModel(
    val name: String,
    val imageUrl: String,
    var isFavorite: Boolean,
) {

    companion object {

        val mockItem: CountryModel
            get() = CountryModel(
                name = "Ukraine",
                imageUrl = "",
                isFavorite = false,
            )
    }
}