package br.com.fiap.repository

import br.com.fiap.model.Pokemon

interface PokemonRepository {
    fun checkHealth(
        onComplete:() -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}