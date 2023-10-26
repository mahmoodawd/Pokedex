package dev.awd.pokedex.data.repository

import androidx.paging.PagingData
import com.example.jetpackcomposepokedex.data.remote.responses.Pokemon
import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokeListPaginated(limit: Int, searchQuery: String): Flow<PagingData<PokedexListEntry>>

    suspend fun getPokemonInfo(name: String): Resource<Pokemon>
}