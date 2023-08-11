package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Produto

class ProdutoDao {
    companion object {
        private val produtos = mutableStateListOf<Produto>()
    }

    fun produtos() = produtos.toList()

    fun salvar(produto: Produto) {
        produtos.add(produto)
    }
}