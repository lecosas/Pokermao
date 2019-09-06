package br.com.fiap.repository

import br.com.fiap.api.PokemonService
import br.com.fiap.model.HealthResponse
import br.com.fiap.model.Pokemon
import br.com.fiap.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(val pokemonService: PokemonService) : PokemonRepository {
    override fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.getPokemons(size, sort)
            .enqueue(object : Callback<PokemonResponse>{
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    if (response.isSuccessful)
                        onComplete(response.body()?.pokemons)
                    else
                        onError(Throwable("Erro ao carregar a lista"))
                }
            })

    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                    if (response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a chamada"))
                    }
                }
            })
    }
}