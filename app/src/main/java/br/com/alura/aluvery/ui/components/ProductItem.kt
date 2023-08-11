package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazillianCurrency
import br.com.alura.aluvery.model.Produto
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun ProductItem(
    produto: Produto,
    modifier: Modifier
) {
    Surface(
        modifier,
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(250.dp, 250.dp)
                .width(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                Modifier
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = produto.imagem,
                    contentDescription = null,
                    Modifier
                        .size(100.dp)
                        .offset(y = 50.dp)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = produto.nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = produto.preco.toBrazillianCurrency(),
                    Modifier.padding(top = 8.dp, bottom = 16.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            produto.descricao?.let {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = it,
                        Modifier.padding(8.dp),
                        color = Color.White
                    )
                }
            }
        }

    }
}

@Preview(
    name = "Preview do Item de Produto",
    showBackground = true
)
@Composable
private fun ProductItemPreview() {
    AluveryTheme {
        Surface {
            ProductItem(
                Produto(
                    nome = LoremIpsum(15).values.first(),
                    preco = BigDecimal("14.99").setScale(2, RoundingMode.UP),
                    descricao = LoremIpsum(50).values.first()
                ),
                Modifier
            )
        }
    }
}