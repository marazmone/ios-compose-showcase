package data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("symbol")
    val symbol: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("current_price")
    val currentPrice: Double? = null,
    @SerialName("market_cap")
    val marketCap: Double? = null,
    @SerialName("market_cap_rank")
    val marketCapRank: Long? = null,
    @SerialName("total_volume")
    val totalValue: Double? = null,
    @SerialName("high_24h")
    val high24h: Double? = null,
    @SerialName("low_24h")
    val low24h: Double? = null,
    @SerialName("price_change_24h")
    val priceChange24h: Double? = null,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null,
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Double? = null,
    @SerialName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double? = null,
    @SerialName("circulating_supply")
    val circulatingSupply: Double? = null,
    @SerialName("total_supply")
    val totalSupply: Double? = null,
    @SerialName("max_supply")
    val maxSupply: Double? = null,
    @SerialName("ath")
    val ath: Double? = null,
    @SerialName("ath_change_percentage")
    val athChangePercentage: Double? = null,
    @SerialName("ath_date")
    val athDate: String? = null,
    @SerialName("atl")
    val atl: Double? = null,
    @SerialName("atl_change_percentage")
    val atlChangePercentage: Double? = null,
    @SerialName("atl_date")
    val atlDate: String? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1hInCurrency: Double? = null,
    @SerialName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: Double? = null,
)