package com.myapplication.preview.currency

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import domain.model.currency.CurrencyListItem
import presentation.ui.AppTheme
import presentation.ui.widget.currency.CurrencyItemWidget

@Preview(showBackground = true)
@Composable
fun CurrencyItem_Up_Preview() {
    AppTheme {
        CurrencyItemWidget(
            CurrencyListItem(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                rank = 1,
                percentChange24H = -2.3,
                _price = 500.0,
                _mCap = 1.57,
                imageUrl = "",
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyItem_Down_Preview() {
    AppTheme {
        CurrencyItemWidget(
            CurrencyListItem(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                rank = 1,
                percentChange24H = 2.3,
                _price = 50123.12321,
                _mCap = 1126003742478.3057,
                imageUrl = "",
            )
        )
    }
}