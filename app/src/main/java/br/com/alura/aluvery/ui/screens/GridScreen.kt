package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.components.ProductItem
import br.com.alura.aluvery.ui.samples.sampleTodos
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun GridScreen(
    produtos: List<Produto>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text(text = "Todos produtos", fontSize = 32.sp)
        }
        items(produtos) {
            ProductItem(produto = it, Modifier)
        }
    }
}


@Preview(
    name = "Preview do App",
    showSystemUi = true
)
@Composable
fun GridScreenPreview() {
    AluveryTheme {
        Surface {
            GridScreen(produtos = sampleTodos, Modifier)
        }
    }
}