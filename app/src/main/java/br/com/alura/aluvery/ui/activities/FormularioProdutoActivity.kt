package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import br.com.alura.aluvery.ui.screens.FormularioProdutoScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.FormularioProdutoViewModel

class FormularioProdutoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val viewModel: FormularioProdutoViewModel by viewModels()
                    FormularioProdutoScreen(
                        viewModel = viewModel,
                        onSalvarClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}



