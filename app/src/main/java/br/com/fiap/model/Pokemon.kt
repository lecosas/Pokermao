package br.com.fiap.model

data class Pokemon (
    val number: Int,
    val name: String,
    val ps: Int,
    val attack: Int,
    val defense: Int,
    val velocity: Int,
    val description: String,
    val imageURL: String
)