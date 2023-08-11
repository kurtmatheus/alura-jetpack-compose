package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.ui.theme.AluveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioTextField(
    fieldText: String,
    fieldLabel: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = fieldText,
        onValueChange = {
            onSearchChange(it)
        },
        modifier = modifier,
        label = { Text(text = fieldLabel) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
fun FormularioTextFieldPreview() {
    AluveryTheme {
        Surface {
            FormularioTextField(
                fieldText = "",
                fieldLabel = "Nome do Produto",
                onSearchChange = {
                    it
                },
                Modifier
            )
        }
    }
}