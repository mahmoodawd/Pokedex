package dev.awd.pokedex.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.awd.pokedex.data.remote.PokemonApi
import dev.awd.pokedex.data.repository.PokemonRepository
import dev.awd.pokedex.data.repository.PokemonRepositoryImpl
import dev.awd.pokedex.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonApi::class.java)

    @Singleton
    @Provides
    fun providePokemonRepo(api: PokemonApi): PokemonRepository =
        PokemonRepositoryImpl(api)
}
