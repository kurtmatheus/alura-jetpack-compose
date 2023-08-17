package br.com.alura.aluvery.ui.states

import br.com.alura.aluvery.model.Produto

data class HomeScreenUiState(
    val searchText: String = "",
    val sections: Map<String, List<Produto>> = mapOf(),
    val onSearchChange: (String) -> Unit = {},
    val searchedProducts: List<Produto> = emptyList()
) {
    fun isShowSection(): Boolean = searchText.isBlank()
}