package br.com.alura.aluvery.ui.samples

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alura.aluvery.model.Produto
import java.math.BigDecimal

val sampleCandies = listOf(
    Produto(
        nome = "Chocolate",
        preco = BigDecimal("3.99"),
        imagem = "https://images.pexels.com/photos/65882/chocolate-dark-coffee-confiserie-65882.jpeg",
        descricao = LoremIpsum(50).values.first()
    ),
    Produto(
        nome = "Sorvete",
        preco = BigDecimal("5.99"),
        imagem = "https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg",
        descricao = LoremIpsum(50).values.first()
    ),
    Produto(
        nome = "Bolo",
        preco = BigDecimal("11.99"),
        imagem = "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg",
    )
)

val sampleDrinks = listOf(
    Produto(
        nome = "Cerveja",
        preco = BigDecimal("5.99"),
        imagem = "https://images.pexels.com/photos/1552630/pexels-photo-1552630.jpeg",
        descricao = LoremIpsum(50).values.first()
    ),
    Produto(
        nome = "Refrigerante",
        preco = BigDecimal("4.99"),
        imagem = "https://images.pexels.com/photos/2775860/pexels-photo-2775860.jpeg"
    ),
    Produto(
        nome = "Suco",
        preco = BigDecimal("7.99"),
        imagem = "https://images.pexels.com/photos/96974/pexels-photo-96974.jpeg",
        descricao = LoremIpsum(50).values.first()
    ),
    Produto(
        nome = "Água",
        preco = BigDecimal("2.99"),
        imagem = "https://images.pexels.com/photos/327090/pexels-photo-327090.jpeg"
    )
)

val sampleProdutos: List<Produto> = listOf(
    Produto(
        nome = "Hamburguer",
        preco = BigDecimal("12.99"),
        imagem = "https://images.pexels.com/photos/1639557/pexels-photo-1639557.jpeg"
    ),
    Produto(
        nome = "Pizza",
        preco = BigDecimal("19.99"),
        imagem = "https://images.pexels.com/photos/825661/pexels-photo-825661.jpeg",
        descricao = LoremIpsum(50).values.first()
    ),
    Produto(
        nome = "Batata frita",
        preco = BigDecimal("7.99"),
        imagem = "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg"
    ), *sampleDrinks.toTypedArray(), *sampleCandies.toTypedArray()
)

val sampleSections = mapOf(
    "Promoções" to sampleProdutos,
    "Doces" to sampleCandies,
    "Bebidas" to sampleDrinks
)