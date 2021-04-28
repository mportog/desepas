package com.mportog.alura.financas.delegate

import com.mportog.alura.financas.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}