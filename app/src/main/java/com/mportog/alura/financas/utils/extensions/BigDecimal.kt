package com.mportog.alura.financas.utils.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.localeCurrency(): String {
    val currencyFormat = DecimalFormat.getCurrencyInstance(Locale.getDefault())
    return currencyFormat.format(this)
}
