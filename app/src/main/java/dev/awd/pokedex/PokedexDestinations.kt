package dev.awd.pokedex

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface PokedexDestinations {

    val route: String
}

object PokemonListDest : PokedexDestinations {
    override val route: String
        get() = "pokemon_list_screen"
}

object DetailsDest : PokedexDestinations {
    override val route: String
        get() = "pokemon_detail_screen"
    const val pokeColorArg = "poke_color"
    const val pokeNameArg = "poke_name"
    val routeWithArgs = "$route/{$pokeNameArg}/{$pokeColorArg}"
    val arguments = listOf(
        navArgument(name = pokeNameArg) {
            type = NavType.StringType
        },
        navArgument(name = pokeColorArg) {
            type = NavType.IntType
        }
    )
}