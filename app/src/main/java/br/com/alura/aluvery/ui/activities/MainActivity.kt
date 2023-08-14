package br.com.alura.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.alura.aluvery.dao.ProdutoDao
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.samples.sampleCandies
import br.com.alura.aluvery.ui.samples.sampleDrinks
import br.com.alura.aluvery.ui.samples.sampleProdutos
import br.com.alura.aluvery.ui.samples.sampleTodos
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.screens.HomeScreenUiState
import br.com.alura.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(
                    Intent(
                        this,
                        FormularioProdutoActivity::class.java
                    )
                )
            }) {
                val produtos = dao.produtos()
                HomeScreen(produtos)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onFabClick: () -> Unit, content: @Composable () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onFabClick,
                        shape = CircleShape
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
//            GridScreen(produtos = sampleProdutos)
        }
    }
}





