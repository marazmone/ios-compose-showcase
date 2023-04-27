package data.mapper.currency

import cache.db.sqldelight.entity.currency.CurrencyEntity
import data.remote.response.CurrencyResponse
import data.utils.orZero
import domain.mapper.base.Mapper
import kotlinx.datetime.Instant

class CurrencyResponseToEntity : Mapper<CurrencyResponse, CurrencyEntity> {

    override fun map(source: CurrencyResponse): CurrencyEntity =
        CurrencyEntity(
            id = source.id.orEmpty(),
            name = source.name.orEmpty(),
            symbol = source.symbol.orEmpty().uppercase(),
            maxSupply = source.maxSupply.orZero,
            circulatingSupply = source.circulatingSupply.orZero,
            totalSupply = source.totalSupply.orZero,
            cmcRank = source.marketCapRank.orZero,
            lastUpdated = source.lastUpdated.orEmpty(),
            price = source.currentPrice.orZero,
            percentChange1h = source.priceChangePercentage1hInCurrency.orZero,
            percentChange24h = source.priceChangePercentage24h.orZero,
            percentChange7d = source.priceChangePercentage7dInCurrency.orZero,
            marketCap = source.marketCap.orZero,
            imageUrl = source.image.orEmpty(),
            high24h = source.high24h.orZero,
            low24h = source.low24h.orZero,
            ath = source.ath.orZero,
            athChangePercentage = source.athChangePercentage.orZero,
            athDate = Instant.parse(source.athDate.orEmpty()).epochSeconds,
            atl = source.atl.orZero,
            atlChangePercentage = source.atlChangePercentage.orZero,
            atlDate = Instant.parse(source.atlDate.orEmpty()).epochSeconds,
            totalValue = source.totalValue.orZero,
        )
}
