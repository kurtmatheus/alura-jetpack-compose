package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import br.com.alura.aluvery.dao.ProdutoDao
import br.com.alura.aluvery.ui.screens.FormularioProdutoScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme

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



