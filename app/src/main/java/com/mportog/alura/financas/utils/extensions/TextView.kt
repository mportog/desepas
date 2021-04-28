package com.mportog.alura.financas.utils.extensions

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mportog.alura.financas.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun TextView.setDespesaColor() {
    this.setTextColor(
        ContextCompat.getColor(
            context,
            R.color.despesa
        )
    )
}

fun TextView.setReceitaColor() {
    this.setTextColor(
        ContextCompat.getColor(
            context,
            R.color.receita
        )
    )
}

fun TextView.setTotalBalanceColor() {
    if (this.text.contains("-")) {
        this.setDespesaColor()
    } else {
        this.setReceitaColor()
    }
}

fun TextView.convertTextToCalendar() : Calendar{
    val text = this.text.toString()
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val dataConvertida: Date = dateFormat.parse(text)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}