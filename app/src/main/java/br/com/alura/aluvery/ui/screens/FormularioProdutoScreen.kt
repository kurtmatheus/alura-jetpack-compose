package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.components.FormularioTextField
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.DecimalFormat

class FormularioScreenUiState(
    val url: String = "",
    val nome: String = "",
    val preco: String = "",
    val descricao: String = "",
    val showImagem: Boolean = url.isNotBlank(),
    val isPriceError: Boolean = false,
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {}
)

@Composable
fun FormularioProdutoScreen(onSalvarClick: (Produto) -> Unit) {
    var name by remember {
        mutableStateOf("")
    }
    var url by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val formatter = remember {
        DecimalFormat("#.##")
    }

    var error by remember {
        mutableStateOf(false)
    }
    FormularioProdutoScreen(
        uiState = FormularioScreenUiState(
            url = url,
            nome = name,
            preco = price,
            descricao = description,
            isPriceError = error,
            onUrlChange = {
                url = it
            },
            onNameChange = {
                name = it
            },
            onPriceChange = {
                try {
                    price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                    error = true
                }
            },
            onDescriptionChange = {
                description = it
            }
        ),
        onSalvarClick = {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val produto = Produto(
                nome = name,
                imagem = url,
                preco = convertedPrice,
                descricao = description
            )
            onSalvarClick(produto)
        }
    )
}

@Composable
fun FormularioProdutoScreen(
    onSalvarClick: () -> Unit = {},
    uiState: FormularioScreenUiState = FormularioScreenUiState()
) {

    val modifierTextField = Modifier.fillMaxWidth()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastrando Produto")
        val url = uiState.url
        val nome = uiState.nome
        val preco = uiState.preco
        val descricao= uiState.descricao
        val isPriceError = uiState.isPriceError

        if (uiState.showImagem) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = modifierTextField
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),

                )
        }
        FormularioTextField(
            fieldText = url,
            fieldLabel = "Url da Imagem",
            onSearchChange = uiState.onUrlChange,
            modifier = modifierTextField,
            keyboardType = KeyboardType.Uri
        )

        FormularioTextField(
            fieldText = nome,
            fieldLabel = "Nome do Produto",
            onSearchChange = uiState.onNameChange,
            modifier = modifierTextField
        )

        FormularioTextField(
            fieldText = preco,
            fieldLabel = "Preço",
            onSearchChange = uiState.onPriceChange,
            Modifier.fillMaxWidth(),
            isError = isPriceError,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next,
        )
        if (isPriceError) {
            Text(
                text = "Preço deve ser um número decimal",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        FormularioTextField(
            fieldText = descricao,
            fieldLabel = "Descrição do Produto",
            onSearchChange = uiState.onDescriptionChange,
            modifier = modifierTextField
                .heightIn(150.dp),
            imeAction = ImeAction.Default
        )
        Button(
            onClick = { onSalvarClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun FormularioProdutoScreenPreview() {
    AluveryTheme {
        Surface {
            FormularioProdutoScreen()
        }
    }
}