package domain.model

data class CountryModel(
    val name: String,
    val imageUrl: String,
) {

    companion object {

        val mockItem: CountryModel
            get() = CountryModel(
                name = "Ukraine",
                imageUrl = "",
            )
    }
}