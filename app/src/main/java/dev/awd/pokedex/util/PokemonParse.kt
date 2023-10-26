package dev.awd.pokedex.util

import androidx.compose.ui.graphics.Color
import dev.awd.pokedex.data.remote.responses.StatsItem
import dev.awd.pokedex.data.remote.responses.TypesItem
import dev.awd.pokedex.ui.theme.AtkColor
import dev.awd.pokedex.ui.theme.DefColor
import dev.awd.pokedex.ui.theme.HPColor
import dev.awd.pokedex.ui.theme.SpAtkColor
import dev.awd.pokedex.ui.theme.SpDefColor
import dev.awd.pokedex.ui.theme.SpdColor
import dev.awd.pokedex.ui.theme.TypeBug
import dev.awd.pokedex.ui.theme.TypeDark
import dev.awd.pokedex.ui.theme.TypeDragon
import dev.awd.pokedex.ui.theme.TypeElectric
import dev.awd.pokedex.ui.theme.TypeFairy
import dev.awd.pokedex.ui.theme.TypeFighting
import dev.awd.pokedex.ui.theme.TypeFire
import dev.awd.pokedex.ui.theme.TypeFlying
import dev.awd.pokedex.ui.theme.TypeGhost
import dev.awd.pokedex.ui.theme.TypeGrass
import dev.awd.pokedex.ui.theme.TypeGround
import dev.awd.pokedex.ui.theme.TypeIce
import dev.awd.pokedex.ui.theme.TypeNormal
import dev.awd.pokedex.ui.theme.TypePoison
import dev.awd.pokedex.ui.theme.TypePsychic
import dev.awd.pokedex.ui.theme.TypeRock
import dev.awd.pokedex.ui.theme.TypeSteel
import dev.awd.pokedex.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: TypesItem): Color {
    return when (type.type?.name?.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: StatsItem): Color {
    return when (stat.stat?.name?.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: StatsItem): String {
    return when (stat.stat?.name?.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}