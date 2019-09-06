package br.com.fiap.di

import br.com.fiap.api.AuthInterceptor
import br.com.fiap.api.PokemonService
import br.com.fiap.repository.PokemonRepository
import br.com.fiap.repository.PokemonRepositoryImpl
import br.com.fiap.view.list.ListPokemonsViewModel
import br.com.fiap.view.splash.SplashViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val viewModelModule = module{
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
}
val repositoryModule = module{
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}
