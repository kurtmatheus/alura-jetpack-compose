package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun ProductSection(
    titulo: String,
    produtos: List<Produto>,
    modifier: Modifier

) {
    Column(modifier) {
        Text(
            text = titulo,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(
                    top = 8.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(produtos) { produto ->
                ProductItem(produto = produto, Modifier)
            }
        }
    }
}

@Preview(
    name = "Preview do Product Section",
    showBackground = true,
)
@Composable
private fun ProductSectionPreview() {
    AluveryTheme {
        Surface {
            ProductSection("Promocoes", sampleProdutos, Modifier)
        }
    }
}