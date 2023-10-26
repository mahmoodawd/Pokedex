package dev.awd.pokedex.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposepokedex.data.mappers.toPokedexListEntry
import com.example.jetpackcomposepokedex.data.remote.responses.PokemonList
import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.data.remote.PokemonApi
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val api: PokemonApi,
    private val searchQuery: String,
) : PagingSource<Int, PokedexListEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexListEntry> {
        return try {

            val nextPageNumber = params.key ?: 1

            val response: PokemonList =
                api.getPokemonList(limit = params.loadSize, offset = nextPageNumber)
            LoadResult.Page(
                data = if (searchQuery.isBlank())
                    response.results.map {
                        it.toPokedexListEntry()
                    } else
                    response.results.filter {
                        it.toPokedexListEntry().doesMatchSearchQuery(searchQuery)
                    }.map { it.toPokedexListEntry() },
                prevKey = response.previous?.offsetParam,
                nextKey = response.next?.offsetParam
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokedexListEntry>): Int? {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)

    }
}