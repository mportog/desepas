package com.mportog.alura.financas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.mportog.alura.financas.R
import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.utils.enums.Tipo
import com.mportog.alura.financas.utils.extensions.localeCurrency
import com.mportog.alura.financas.utils.extensions.localeDate
import kotlinx.android.synthetic.main.transacao_item.view.transacao_valor
import kotlinx.android.synthetic.main.transacao_item.view.transacao_data
import kotlinx.android.synthetic.main.transacao_item.view.transacao_icone
import kotlinx.android.synthetic.main.transacao_item.view.transacao_categoria

class ListaTransacoesAdapter(
    private val _transacoes: List<Transacao>,
    private val _context: Context
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewCriada =
            LayoutInflater.from(_context).inflate(R.layout.transacao_item, parent, false)

        val transacao = _transacoes[position]

        setViewData(transacao, viewCriada)
        setTextcolor(transacao, viewCriada)

        return viewCriada

    }

    private fun setTextcolor(transacao: Transacao, viewCriada: View) {
        if (transacao.tipo == Tipo.DESPEZA) {

            viewCriada.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    _context,
                    R.color.despesa
                )
            )

            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)

        } else {

            viewCriada.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    _context,
                    R.color.receita
                )
            )

            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }
    }

    private fun setViewData(transacao: Transacao, viewCriada: View) {

        viewCriada.transacao_valor.text = transacao.valor.localeCurrency()
        viewCriada.transacao_categoria.text = transacao.categoria
        viewCriada.transacao_data.text = transacao.data.localeDate()
    }

    override fun getItem(position: Int): Transacao {
        return _transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return _transacoes.size
    }

}