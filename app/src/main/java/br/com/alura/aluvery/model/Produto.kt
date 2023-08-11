package br.com.alura.aluvery.model

import java.math.BigDecimal

data class Produto(
    val nome: String,
    val preco: BigDecimal,
    val imagem: String? = null,
    val descricao: String? = null
)
