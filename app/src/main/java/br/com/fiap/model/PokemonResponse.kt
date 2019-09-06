package br.com.fiap.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @SerializedName("content") val pokemons: List<Pokemon>,
    val totalPages: Int

)