package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.samples.sampleCandies
import br.com.alura.aluvery.ui.samples.sampleDrinks
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.samples.sampleSections
import br.com.alura.aluvery.ui.samples.sampleTodos
import br.com.alura.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(
    val searchText: String = "",
    val sections: Map<String, List<Produto>> = mapOf(),
    val onSearchChange: (String) -> Unit = {},
    val searchedProducts: List<Produto> = emptyList()
) {
    fun isShowSection(): Boolean = searchText.isBlank()
}

@Composable
fun HomeScreen(produtos: List<Produto>) {
    val sections = mapOf(
        "Todos os Produtos" to produtos,
        "Promoções" to sampleProdutos,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var text by remember {
        mutableStateOf("")
    }

    val searchedProducts = if (text.isNotBlank()) {
        val todos = sampleTodos + produtos
        todos.filter { produto -> produto.nome.contains(text, true) }
    } else emptyList()

    val uiState = remember(produtos, text) {
        HomeScreenUiState(
            searchText = text,
            sections = sections,
            onSearchChange = {
                text = it
            },
            searchedProducts = searchedProducts
        )
    }

    HomeScreen(uiState)
}

@Composable
fun HomeScreen(
    uiState: HomeScreenUiState
) {
    val sections = uiState.sections

    Column {
        SearchTextField(
            searchText = uiState.searchText,
            onSearchChange = uiState.onSearchChange
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier)
            }
            if (uiState.isShowSection()) {
                for (section in sections) {
                    val titulo = section.key
                    val produtos = section.value
                    item {
                        ProductSection(
                            titulo = "$titulo (${produtos.size})", produtos = produtos, Modifier
                        )
                    }
                }
            } else {
                items(uiState.searchedProducts) { produto ->
                    CardProductItem(
                        product = produto, modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier)
            }
        }
    }
}


@Preview(
    name = "Preview do App", showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(
                searchText = "",
                sections = sampleSections,
                onSearchChange = {},
                searchedProducts = sampleProdutos
            ))
        }
    }
}