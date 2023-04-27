package presentation.model

import data.utils.amountWithCurrency
import data.utils.formattedDate
import domain.model.currency.CurrencyDetail

sealed class CurrencyDetailOtherInfo(val title: String) {

    class Simple(title: String, val text: String) : CurrencyDetailOtherInfo(title)

    /**
     * All time high or All time low
     */
    class AllTime(
        title: String,
        val percentChange: Double,
        private val _price: Double,
        private val _date: Long,
    ) : CurrencyDetailOtherInfo(title) {

        val price: String get() = _price.amountWithCurrency()

        val date: String get() = _date.formattedDate
    }
}

val CurrencyDetail.otherInfo: List<CurrencyDetailOtherInfo>
    get() = listOf(
        CurrencyDetailOtherInfo.Simple(
            title = "Traded Volume",
            text = tradingVolume.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.Simple(
            title = "24h MAX",
            text = max24H.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.Simple(
            title = "24h MIN",
            text = min24H.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.AllTime(
            title = "Absolute maximum",
            percentChange = athPercentChange,
            _price = athPrice,
            _date = athDate
        ),
        CurrencyDetailOtherInfo.AllTime(
            title = "Absolute minimum",
            percentChange = atlPercentChange,
            _price = atlPrice,
            _date = atlDate
        ),
    )