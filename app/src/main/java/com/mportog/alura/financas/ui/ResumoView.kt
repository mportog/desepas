package com.mportog.alura.financas.ui

import android.view.View
import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.utils.Resumo
import com.mportog.alura.financas.utils.extensions.localeCurrency
import com.mportog.alura.financas.utils.extensions.setDespesaColor
import com.mportog.alura.financas.utils.extensions.setReceitaColor
import com.mportog.alura.financas.utils.extensions.setTotalBalanceColor
import kotlinx.android.synthetic.main.resumo_card.view.resumo_card_total
import kotlinx.android.synthetic.main.resumo_card.view.resumo_card_receita
import kotlinx.android.synthetic.main.resumo_card.view.resumo_card_despesa

class ResumoView(private val view: View, transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)

    fun atualizaResumo() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaTotal() {
        with(view.resumo_card_total)
        {
            text = resumo.total.localeCurrency()
            view.resumo_card_total.setTotalBalanceColor()
        }
    }

    private fun adicionaDespesa() {
        with(view.resumo_card_despesa)
        {
            text = resumo.despesa.localeCurrency()
            view.resumo_card_despesa.setDespesaColor()
        }
    }

    private fun adicionaReceita() {
        with(view.resumo_card_receita)
        {
            text = resumo.receita.localeCurrency()
            setReceitaColor()
        }
    }
}