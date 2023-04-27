package domain.model.currency

import data.utils.amountWithCurrency

data class CurrencyDetail(
    val id: String,
    val name: String,
    var isFavorite: Boolean = false,
    val percentChange1H: Double,
    val percentChange24H: Double,
    val percentChange7D: Double,
    private val _price: Double,
    val symbol: String,
    val rank: Long,
    val imageUrl: String,
    private val _marketCap: Double,
    private val _supplyCirculating: Double,
    private val _supplyTotal: Double,
    private val _supplyMax: Double,
    val tradingVolume: Double,
    val max24H: Double,
    val min24H: Double,
    val athDate: Long,
    val athPrice: Double,
    val athPercentChange: Double,
    val atlDate: Long,
    val atlPrice: Double,
    val atlPercentChange: Double,
) {

    val price: String
        get() = _price.amountWithCurrency()
    val marketCap: String
        get() = _marketCap.amountWithCurrency()
    val supplyCirculating: String
        get() = _supplyCirculating.toString()
    val supplyTotal: String
        get() = if (_supplyTotal == 0.0) "∞" else _supplyTotal.toString()
    val supplyMax: String
        get() = if (_supplyMax == 0.0) "∞" else _supplyMax.toString()
    val supplyProgress: Int
        get() = (_supplyCirculating / (if (_supplyMax == 0.0) _supplyTotal else _supplyMax) * 100).toInt()

    companion object {

        val template: CurrencyDetail
            get() = CurrencyDetail(
                "",
                "Empty Token",
                false,
                4.0,
                1.4,
                -5.4,
                223.44,
                "SOL",
                5,
                "",
                123321123.12332120,
                12332.0,
                123321.0,
                1233211.0,
                max24H = 46000.0,
                min24H = 40000.0,
                tradingVolume = 149009900.0,
                athDate = 1638776303,
                athPrice = 69000.0,
                athPercentChange = -35.0,
                atlPrice = 35.0,
                atlPercentChange = 10000.0,
                atlDate = 1386315503,
            )
    }
}
