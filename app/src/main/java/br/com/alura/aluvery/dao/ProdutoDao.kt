package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Produto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class ProdutoDao {
    companion object {
        private val produtos = MutableStateFlow<List<Produto>>(emptyList())
    }

    fun produtos() = produtos.asStateFlow()

    fun salvar(produto: Produto) {
        produtos.value += produto
    }
}