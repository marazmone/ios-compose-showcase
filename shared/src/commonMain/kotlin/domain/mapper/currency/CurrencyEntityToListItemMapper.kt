package domain.mapper.currency

import cache.db.sqldelight.entity.currency.CurrencyEntity
import data.utils.orZero
import domain.mapper.base.Mapper
import domain.model.currency.CurrencyListItem

class CurrencyEntityToListItemMapper : Mapper<CurrencyEntity, CurrencyListItem> {

    override fun map(source: CurrencyEntity): CurrencyListItem =
        CurrencyListItem(
            id = source.id,
            rank = source.cmcRank.orZero,
            symbol = source.symbol.uppercase(),
            name = source.name,
            percentChange24H = source.percentChange24h.orZero,
            _price = source.price.orZero,
            _mCap = source.marketCap.orZero,
            imageUrl = source.imageUrl,
        )
}
