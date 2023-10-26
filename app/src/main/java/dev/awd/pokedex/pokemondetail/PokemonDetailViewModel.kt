package dev.awd.pokedex.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposepokedex.data.remote.responses.Pokemon
import dev.awd.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.awd.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokeInfo(pokeName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokeName)
    }
}