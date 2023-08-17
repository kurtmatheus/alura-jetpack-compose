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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.ui.components.FormularioTextField
import br.com.alura.aluvery.ui.states.FormularioProdutoUiState
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.FormularioProdutoViewModel
import coil.compose.AsyncImage

@Composable
fun  FormularioProdutoScreen(
    viewModel: FormularioProdutoViewModel,
    onSalvarClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    FormularioProdutoScreen(
        uiState = uiState,
        onSalvarClick = {
            viewModel.salvar()
            onSalvarClick()
        }
    )
}

@Composable
fun FormularioProdutoScreen(
    onSalvarClick: () -> Unit = {},
    uiState: FormularioProdutoUiState = FormularioProdutoUiState()
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

        if (uiState.showImagem()) {
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
            onClick = onSalvarClick,
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