package dev.awd.pokedex.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import dev.awd.pokedex.R
import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.ui.theme.PokedexTheme


@Composable
fun PokemonListScreen(
    onItemClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val isSearching by viewModel.isSearching.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val paginatedPokemonList: LazyPagingItems<PokedexListEntry> =
        viewModel.pokemonList.collectAsLazyPagingItems()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.heightIn(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.onSearchTextChanged(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                PokemonList(
                    paginatedPokemonList = paginatedPokemonList,
                    onItemClick = onItemClick
                )
            }
        }

    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.hasFocus && text.isBlank()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }

}

@Composable
fun PokemonList(
    onItemClick: (String, Int) -> Unit,
    paginatedPokemonList: LazyPagingItems<PokedexListEntry>
) {


    /* Get Data Using Paging Library*/
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center,
    ) {

        when (paginatedPokemonList.loadState.refresh) {
            is LoadState.Error -> {
                RetrySection(
                    error = "Error Loading Poke`mons"
                ) {
                    paginatedPokemonList.retry()
                }
            }

            LoadState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(count = paginatedPokemonList.itemCount,
//                         key = paginatedPokemonList.itemKey { it.number },
                        contentType = paginatedPokemonList.itemContentType { "MyPagingItems" }) { index ->
                        PokedexEntry(
                            entry = paginatedPokemonList[index]!!,
                            onPokemonItemClick = onItemClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    modifier: Modifier = Modifier,
    onPokemonItemClick: (String, Int) -> Unit,
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }

    Column(modifier = modifier
        .wrapContentSize()
        .shadow(5.dp, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .aspectRatio(1f)
        .background(
            Brush.verticalGradient(
                listOf(
                    dominantColor,
                    defaultDominantColor
                )
            )
        )
        .clickable {
            val pokeName = entry.pokemonName
            val pokeColor = dominantColor.toArgb()
            onPokemonItemClick(pokeName, pokeColor)
        }) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entry.imageUrl)
                .crossfade(true)
                .placeholder(R.drawable.ic_international_pok_mon_logo)
                .error(R.drawable.ic_international_pok_mon_logo)
                .build(),
            contentDescription = entry.pokemonName,
            contentScale = ContentScale.FillBounds,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Center)
                        .scale(.25f)
                )
            },
            modifier = Modifier
                .size(120.dp)
                .align(CenterHorizontally)
        )
        Text(
            text = entry.pokemonName,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(CenterHorizontally)
        )

    }
}


@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp),
        horizontalAlignment = CenterHorizontally) {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PokeListPreview() {
    PokedexTheme {
        PokedexEntry(
            entry = PokedexListEntry("Poke", "", 1),
            onPokemonItemClick = { _, _ -> })
    }
}