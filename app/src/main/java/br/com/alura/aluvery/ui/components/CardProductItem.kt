package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazillianCurrency
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage

@Composable
fun CardProductItem(
    product: Produto,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable { expanded = !expanded }
    ) {
        Column {
            AsyncImage(
                model = product.imagem,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.nome
                )
                Text(
                    text = product.preco.toBrazillianCurrency()
                )
            }
            val dados = object {
                val textOverflow =
                    if (expanded) TextOverflow.Visible
                    else TextOverflow.Ellipsis
                val maxLines =
                    if (expanded) Int.MAX_VALUE
                    else 2
            }
            product.descricao?.let {

                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    overflow = dados.textOverflow,
                    maxLines = dados.maxLines
                )
            }
        }
    }
}


@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = sampleProdutos[1],
                Modifier
            )
        }
    }
}