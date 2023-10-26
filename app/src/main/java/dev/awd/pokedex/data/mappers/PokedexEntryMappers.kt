package com.example.jetpackcomposepokedex.data.mappers

import dev.awd.pokedex.data.models.PokedexListEntry
import dev.awd.pokedex.data.remote.responses.Result
import java.util.Locale

fun Result.toPokedexListEntry(): PokedexListEntry {
    val number = if (url!!.endsWith("/")) {
        url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        url.takeLastWhile { it.isDigit() }
    }
    val url =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
    return PokedexListEntry(
        pokemonName = name!!.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        number = number.toInt(),
        imageUrl = url
    )
}