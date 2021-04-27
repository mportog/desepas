package com.mportog.alura.financas.utils.extensions

import java.text.DateFormat
import java.util.Calendar

fun Calendar.localeDate(): String {
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    return dateFormat.format(this.time)
}