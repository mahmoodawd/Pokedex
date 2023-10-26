package dev.awd.pokedex.data.models

data class PokedexListEntry(

    val pokemonName: String,
    val imageUrl: String,
    val number: Int
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            pokemonName,
            "$number",
            "${pokemonName.first()}"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

