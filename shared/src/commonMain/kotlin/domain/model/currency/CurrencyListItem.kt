package domain.model.currency

import kotlin.random.Random

data class CurrencyListItem(
    val id: String,
    val rank: Long,
    val symbol: String,
    val name: String,
    val percentChange24H: Double,
    val imageUrl: String,
    private val _price: Double,
    private val _mCap: Double,
) {

    val title: String
        get() = "#"
            .plus(rank)
            .plus(" ")
            .plus(symbol)

    val price: String get() = _price.toString()

    fun mCap(mCapText: String) = mCapText
        .plus(" ")
        .plus(_mCap)
        .plus(" $")

    companion object {

        private fun mockItem(rank: Long): CurrencyListItem = CurrencyListItem(
            id = Random.nextInt().toString(),
            name = "Currency name",
            symbol = "SML",
            rank = rank,
            percentChange24H = Random.nextDouble(-90.0, 90.0),
            _price = Random.nextDouble(1.0, 50_000.0),
            _mCap = Random.nextDouble(100_000_000.0, 10_000_000_000.0),
            imageUrl = "",
        )

        fun mockList(): List<CurrencyListItem> = List(100) {
            mockItem(it.toLong())
        }
    }
}
