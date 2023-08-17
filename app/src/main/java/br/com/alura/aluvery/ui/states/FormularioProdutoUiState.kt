package br.com.alura.aluvery.ui.states

data class FormularioProdutoUiState(
    val url: String = "",
    val nome: String = "",
    val preco: String = "",
    val descricao: String = "",
    var isPriceError: Boolean = false,
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {}
) {
    fun showImagem() = url.isNotBlank()
}