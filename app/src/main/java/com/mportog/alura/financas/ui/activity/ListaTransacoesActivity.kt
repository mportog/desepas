package com.mportog.alura.financas.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mportog.alura.financas.R
import com.mportog.alura.financas.delegate.TransacaoDelegate
import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.ui.ResumoView
import com.mportog.alura.financas.ui.adapter.ListaTransacoesAdapter
import com.mportog.alura.financas.ui.dialog.TransacaoDialog
import com.mportog.alura.financas.utils.enums.Tipo
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val transacoes: MutableList<Transacao> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()

        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.RECEITA)
            }
        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.DESPESA)
            }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        TransacaoDialog(window.decorView as ViewGroup, this)
            .iniciar(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun configuraLista() {
        lista_transacoes_listview.setAdapter(ListaTransacoesAdapter(transacoes, this))
    }

    private fun configuraResumo() {
        val view: View = window.decorView
        val resumoView = ResumoView(view, transacoes)
        resumoView.atualizaResumo()
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }
}
