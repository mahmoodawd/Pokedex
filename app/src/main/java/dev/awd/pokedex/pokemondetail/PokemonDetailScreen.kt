package dev.awd.pokedex.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.jetpackcomposepokedex.data.remote.responses.Pokemon
import dev.awd.pokedex.R
import dev.awd.pokedex.data.remote.responses.StatsItem
import dev.awd.pokedex.data.remote.responses.TypesItem
import dev.awd.pokedex.util.Resource
import dev.awd.pokedex.util.parseStatToAbbr
import dev.awd.pokedex.util.parseStatToColor
import dev.awd.pokedex.util.parseTypeToColor
import java.util.Locale
import kotlin.math.round

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    onBackPressed: () -> Unit = {},
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokeInfo(pokemonName)
    }.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(bottom = 16.dp)
    ) {
        PokemonDetailTopSection(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter),
            onBackPressed = onBackPressed
        )

        PokemonDetailStateWrapper(
            pokemonInfo = pokemonInfo,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (pokemonInfo is Resource.Success) {
                pokemonInfo.data?.sprites?.let {
                    AsyncImage(
                        model = it.frontDefault,
                        contentDescription = pokemonInfo.data.name,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(y = topPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonDetailTopSection(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    onBackPressed()
                }
        )
    }

}


@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (pokemonInfo) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokeInfo = pokemonInfo.data!!,
                modifier = modifier
            )
        }

        is Resource.Error -> {
            Text(
                text = pokemonInfo.message!!,
                color = MaterialTheme.colorScheme.error,
                modifier = modifier
            )
        }

        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = loadingModifier
            )

        }
    }

}

@Composable
fun PokemonDetailSection(
    pokeInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 70.dp)
            .verticalScroll(scrollState)
    ) {
        PokemonHeaderSection(pokeId = pokeInfo.id!!, pokeName = pokeInfo.name!!)
        PokemonTypeSection(types = pokeInfo.types)
        PokemonDetailDataSection(
            pokeWeight = pokeInfo.weight!!,
            pokeHeight = pokeInfo.height!!
        )
        PokemonBaseStats(pokeStats = pokeInfo.stats)
    }
}

@Composable
fun PokemonHeaderSection(
    modifier: Modifier = Modifier,
    pokeId: Int,
    pokeName: String
) {
    Text(
        text = "#${pokeId} ${pokeName?.capitalize(Locale.ROOT)}",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun PokemonTypeSection(
    types: List<TypesItem>,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .height(35.dp)
            ) {
                Text(
                    text = type?.type?.name?.capitalize(Locale.ROOT) ?: "",
                    color = Color.White,
                    fontSize = 18.sp
                )

            }
        }
    }
}

@Composable
fun PokemonDetailDataSection(
    pokeWeight: Int,
    pokeHeight: Int,
    sectionHeight: Dp = 80.dp,

    ) {
    val pokeWeightInKG = remember {
        round(pokeWeight * 100f) / 1000
    }
    val pokeHeightInMeters = remember {
        round(pokeHeight * 100f) / 1000
    }

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonDetailDataItem(
            dataValue = pokeWeightInKG,
            dataUnit = "Kg",
            dataIcon = painterResource(id = R.drawable.ic_weight),
            modifier = Modifier
                .weight(1f)
        )
        Spacer(
            modifier = Modifier
                .size(1.dp, sectionHeight)
                .background(
                    Color.LightGray
                )
        )
        PokemonDetailDataItem(
            dataValue = pokeHeightInMeters,
            dataUnit = "m",
            dataIcon = painterResource(id = R.drawable.ic_height),
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun PokemonDetailDataItem(
    dataValue: Float,
    dataUnit: String,
    dataIcon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = dataIcon, contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$dataValue$dataUnit",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun PokemonBaseStats(
    pokeStats: List<StatsItem>,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokeStats.maxOf { it.baseStat!!.toDouble() }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Base Stats: ",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (index in pokeStats.indices) {
            val stat = pokeStats[index]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat!!,
                statMaxValue = maxBaseStat.toInt(),
                statColor = parseStatToColor(stat),
                animationDelay = index * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 28.dp,
    animationDuration: Int = 10000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        label = "",
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(currentPercent.value)
                .clip(CircleShape)
                .background(statColor)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = statName,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = (currentPercent.value * statMaxValue).toInt().toString(),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
