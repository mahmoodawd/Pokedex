package dev.awd.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.example.jetpackcomposepokedex.data.remote.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.data.remote.PokemonApi
import dev.awd.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override fun getPokeListPaginated(
        limit: Int,
        searchQuery: String
    ): Flow<PagingData<PokedexListEntry>> = Pager(
        PagingConfig(pageSize = limit)
    ) {
        PokemonPagingSource(api, searchQuery)
    }.flow


    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> =
        try {
            val pokeList = api.getPokemonList(limit, offset)
            Resource.Success(pokeList)
        } catch (e: Exception) {
            Timber.e("Poke Repo Error:  ${e.message}")
            Resource.Error(e.message ?: "Unknown Error")
        }

    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> =
        try {
            Resource.Success(api.getPokemonInfo(name))
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
}

