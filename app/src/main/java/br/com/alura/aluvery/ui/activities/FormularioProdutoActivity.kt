package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import br.com.alura.aluvery.dao.ProdutoDao
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.components.FormularioTextField
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.DecimalFormat

class FormularioProdutoActivity : ComponentActivity() {

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    FormularioProdutoScreen(onSalvarClick = { produto ->
                        dao.salvar(produto)
                        finish()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun FormularioProdutoScreen(
    onSalvarClick: (Produto) -> Unit = {}
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
        var url by remember { mutableStateOf("") }
        if (url.isNotBlank()) {
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
            onSearchChange = { url = it },
            modifier = modifierTextField,
            keyboardType = KeyboardType.Uri
        )
        var nome by remember { mutableStateOf("") }
        FormularioTextField(
            fieldText = nome,
            fieldLabel = "Nome do Produto",
            onSearchChange = { nome = it },
            modifier = modifierTextField
        )
        var preco by remember { mutableStateOf("") }
        var isPriceError by remember { mutableStateOf(false) }
        val formatter = DecimalFormat("#.##")
        FormularioTextField(
            fieldText = preco,
            fieldLabel = "Preço",
            onSearchChange = {
                isPriceError = try {
                    BigDecimal(it)
                    false
                } catch (e: IllegalArgumentException) {
                    it.isNotEmpty()
                }
                preco = it
            },
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
        var descricao by remember { mutableStateOf("") }
        FormularioTextField(
            fieldText = descricao,
            fieldLabel = "Descrição do Produto",
            onSearchChange = { descricao = it },
            modifier = modifierTextField
                .heightIn(150.dp),
            imeAction = ImeAction.Default
        )
        Button(
            onClick = {
                val precoConvertido = try {
                    BigDecimal(preco)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val produto = Produto(
                    nome = nome,
                    preco = precoConvertido,
                    imagem = url,
                    descricao = descricao
                )
                onSalvarClick(produto)
            },
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

