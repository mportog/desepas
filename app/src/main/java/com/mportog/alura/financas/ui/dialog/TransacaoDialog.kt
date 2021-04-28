package com.mportog.alura.financas.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mportog.alura.financas.R
import com.mportog.alura.financas.delegate.TransacaoDelegate
import com.mportog.alura.financas.model.Transacao
import com.mportog.alura.financas.utils.enums.Tipo
import com.mportog.alura.financas.utils.extensions.convertTextToCalendar
import com.mportog.alura.financas.utils.extensions.localeDate
import kotlinx.android.synthetic.main.form_transacao.view.form_transacao_categoria
import kotlinx.android.synthetic.main.form_transacao.view.form_transacao_data
import kotlinx.android.synthetic.main.form_transacao.view.form_transacao_valor
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.util.Calendar

class TransacaoDialog(private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = criaLayout()
    private val campoValor = viewCriada.form_transacao_valor
    private val campoCategoria = viewCriada.form_transacao_categoria
    private val campoData = viewCriada.form_transacao_data

    val hoje = Calendar.getInstance()

    fun iniciar(
        tipo: Tipo,
        transacaoDelegate: TransacaoDelegate
    ) {
        configuraData()
        configuraCategoria(tipo)
        configuraFormulario(tipo, transacaoDelegate)
    }

    private fun configuraFormulario(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(configuraTitulo(tipo))
            .setView(viewCriada)
            .setPositiveButton("Adicionar") { _, _ ->
                val textValue = campoValor.text.toString()
                val category = campoCategoria.selectedItem.toString()

                val value = converteValor(textValue)
                val data = campoData.convertTextToCalendar()

                val transacaoCriada = Transacao(
                    valor = value,
                    tipo = tipo,
                    categoria = category,
                    data = data
                )
                transacaoDelegate.delegate(transacaoCriada)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun configuraTitulo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }

    private fun converteValor(textValue: String): BigDecimal {
        return try {
            BigDecimal(textValue)
        } catch (exeception: NumberFormatException) {
            Toast.makeText(
                context,
                "Transação sem valor",
                Toast.LENGTH_LONG
            )
                .show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCategoria(tipo: Tipo) {

        val categoriaArray = if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }

        val adapter = ArrayAdapter.createFromResource(
            context,
            categoriaArray,
            android.R.layout.simple_spinner_dropdown_item
        )

        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configuraData() {
        campoData.setText(hoje.localeDate())

        campoData.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    campoData.setText(dataSelecionada.localeDate())
                },
                hoje.get(Calendar.YEAR),
                hoje.get(Calendar.MONTH),
                hoje.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }
}