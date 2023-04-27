package data.remote

object RemoteConst {

    object Url {

        const val BASE = "https://api.coingecko.com/api/v3/"

        const val GET_ALL = BASE.plus("coins/markets")
    }

    object QueryParams {

        const val VS_CURRENCY = "vs_currency"
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
        const val ORDER = "order"
        const val PRICE_CHANGE_PERCENTAGE = "price_change_percentage"
        const val IDS = "ids"
    }
}