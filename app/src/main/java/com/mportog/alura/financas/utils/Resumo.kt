package com.mportog.alura.financas.utils

import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.utils.enums.Tipo
import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = calculateByType(Tipo.RECEITA)
    val despesa get() = calculateByType(Tipo.DESPESA)
    val total get() = receita.subtract(despesa)

    private fun calculateByType(tipo: Tipo): BigDecimal {
        return transacoes.filter { transacao -> transacao.tipo == tipo }
            .sumByDouble { transacao ->
                transacao.valor.toDouble()
            }.toBigDecimal()
    }
}