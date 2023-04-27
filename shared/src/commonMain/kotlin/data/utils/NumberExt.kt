package data.utils

import data.Currency.Fiat.USD
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val Double?.orZero: Double get() = this ?: 0.0

val Long?.orZero: Long get() = this ?: 0L

val Int?.orZero: Int get() = this ?: 0

val Long.secToMs: Long get() = this * 1000

val Long.msToSec: Long get() = this / 1000

fun Double.amountWithCurrency(currency: String = USD): String = this.toString().plus(currency)

val Long.formattedDate: String
    get() {
        val localDateTime = Instant
            .fromEpochMilliseconds(this.secToMs)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val date = localDateTime.date
        TimeZone.availableZoneIds
        val day = date.dayOfMonth
        val month = date.monthNumber
        val year = date.year
        return "${day.zeroPrefixed(2)}.${month.zeroPrefixed(2)}.${year}"
    }

fun Int.zeroPrefixed(
    maxLength: Int,
): String {
    if (this < 0 || maxLength < 1) return ""

    val string = this.toString()
    val currentStringLength = string.length
    return if (maxLength <= currentStringLength) {
        string
    } else {
        val diff = maxLength - currentStringLength
        var prefixedZeros = ""
        repeat(diff) {
            prefixedZeros += "0"
        }
        "$prefixedZeros$string"
    }
}