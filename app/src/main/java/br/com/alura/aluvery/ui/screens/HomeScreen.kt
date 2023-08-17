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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.samples.sampleSections
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
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
            HomeScreen(
                HomeScreenUiState(
                    searchText = "",
                    sections = sampleSections,
                    onSearchChange = {},
                    searchedProducts = sampleProdutos
                )
            )
        }
    }
}