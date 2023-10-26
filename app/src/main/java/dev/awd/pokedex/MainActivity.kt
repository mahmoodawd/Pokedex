package dev.awd.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.awd.pokedex.pokemondetail.PokemonDetailScreen
import dev.awd.pokedex.pokemonlist.PokemonListScreen
import dev.awd.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokedexNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun PokedexNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = PokemonListDest.route
    ) {
        composable(route = PokemonListDest.route) {
            PokemonListScreen(onItemClick = { pokeName, pokeDominantColor ->
                navController.navigate(
                    "${DetailsDest.route}/${pokeName}/${pokeDominantColor}"
                )
            })
        }
        composable(
            route = DetailsDest.routeWithArgs,
            arguments = DetailsDest.arguments
        ) { navBackStackEntry ->
            val dominantColor = remember {
                val color = navBackStackEntry.arguments?.getInt(DetailsDest.pokeColorArg)
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                navBackStackEntry.arguments?.getString(DetailsDest.pokeNameArg)
            }
            PokemonDetailScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase() ?: "",
                onBackPressed = { navController.popBackStack() }
            )

        }
    }

}

