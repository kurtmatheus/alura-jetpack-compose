package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.dao.ProdutoDao
import br.com.alura.aluvery.ui.samples.sampleCandies
import br.com.alura.aluvery.ui.samples.sampleDrinks
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProdutoDao()

    private val produtos = dao.produtos()

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {

        _uiState.update { curState ->
            curState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        searchedProducts = searchProducts(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            produtos.collect { produtosDao ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos os Produtos" to produtosDao,
                        "Promoções" to sampleProdutos,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchProducts(uiState.value.searchText)
                )
            }
        }
    }

    private fun searchProducts(text: String) = if (text.isNotBlank()) {
        val todos = sampleProdutos + produtos.value
        todos.filter { produto -> produto.nome.contains(text, true) }
    } else emptyList()

}