package com.mportog.alura.financas.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mportog.alura.financas.R
import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.ui.adapter.ListaTransacoesAdapter
import com.mportog.alura.financas.utils.enums.Tipo
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes = createData()
        lista_transacoes_listview.setAdapter(ListaTransacoesAdapter(transacoes, this))
    }

    private fun createData(): List<Transacao> {
        val ontem: Calendar = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        }

        var transacoes = listOf(
            Transacao(valor = BigDecimal(50.50), tipo = Tipo.RECEITA),
            Transacao(
                valor = BigDecimal(27.50),
                categoria = "Transporte",
                tipo = Tipo.DESPEZA,
                data = ontem
            ),
            Transacao(valor = BigDecimal(42.0), tipo = Tipo.DESPEZA, data = ontem)
        )
        return transacoes
    }
}