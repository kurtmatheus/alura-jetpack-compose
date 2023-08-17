package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProdutoDao
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.states.FormularioProdutoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class FormularioProdutoViewModel : ViewModel() {

    private val dao = ProdutoDao()

    private val _uiState = MutableStateFlow(FormularioProdutoUiState())
    val uiState get() = _uiState.asStateFlow()

    private val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { curState ->
            curState.copy(
                onUrlChange = { url ->
                    _uiState.value = _uiState.value.copy(
                        url = url
                    )
                },
                onNameChange = { nome ->
                    _uiState.value = _uiState.value.copy(
                        nome = nome
                    )
                },
                onPriceChange = { preco ->
                    val precoAtualizado = try {
                        formatter.format(BigDecimal(preco))
                    } catch (e: IllegalArgumentException) {
                        _uiState.value.isPriceError = true
                        if (preco.isEmpty()) {
                            preco
                        } else {
                            null
                        }
                    }
                    precoAtualizado?.let {
                        _uiState.value = _uiState.value.copy(
                            preco = precoAtualizado
                        )
                    }
                },
                onDescriptionChange = { descricao ->
                    _uiState.value = _uiState.value.copy(
                        descricao = descricao
                    )
                }

            )
        }
    }

    fun salvar() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(preco)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val produto = Produto(
                nome = nome,
                imagem = url,
                preco = convertedPrice,
                descricao = descricao
            )
            dao.salvar(produto)
        }
    }
}