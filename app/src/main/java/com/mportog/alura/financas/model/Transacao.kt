package com.mportog.alura.financas.model

import com.mportog.alura.financas.utils.enums.Tipo
import java.math.BigDecimal
import java.util.Calendar

class Transacao(
    val valor: BigDecimal,
    val categoria: String = "Indefinido",
    val tipo: Tipo = Tipo.INDEFINIDO,
    val data: Calendar = Calendar.getInstance()
)