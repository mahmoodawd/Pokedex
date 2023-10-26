package dev.awd.pokedex.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.data.repository.PokemonRepository
import dev.awd.pokedex.util.Constants.PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    repository: PokemonRepository
) : ViewModel() {

    var isSearching = MutableStateFlow(false)
        private set
    var searchText = MutableStateFlow("")
        private set

    /*var pokemonList: Flow<PagingData<PokedexListEntry>> =
        repository.getPokeListPaginated(PAGE_SIZE, searchText.value)
            .cachedIn(viewModelScope)
        private set*/

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pokemonList: Flow<PagingData<PokedexListEntry>> =
        searchText
            .debounce(300L)
            .onEach { isSearching.update { true } }
            .flatMapLatest { query ->
                repository.getPokeListPaginated(PAGE_SIZE, query)
            }.onEach {
                isSearching.update { false }
            }.cachedIn(viewModelScope)


    fun onSearchTextChanged(text: String) {
        searchText.value = text
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val map = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(map).generate() { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}

