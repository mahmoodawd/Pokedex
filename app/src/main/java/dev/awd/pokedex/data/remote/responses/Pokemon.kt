package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.AbilitiesItem
import dev.awd.pokedex.data.remote.responses.Species
import dev.awd.pokedex.data.remote.responses.Sprites
import dev.awd.pokedex.data.remote.responses.StatsItem
import dev.awd.pokedex.data.remote.responses.TypesItem

data class Pokemon(

    @field:SerializedName("location_area_encounters")
    val locationAreaEncounters: String? = null,

    @field:SerializedName("types")
    val types: List<TypesItem>,

    @field:SerializedName("base_experience")
    val baseExperience: Int? = null,

    @field:SerializedName("held_items")
    val heldItems: List<HeldItemsItem?>? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("is_default")
    val isDefault: Boolean? = null,

    @field:SerializedName("past_types")
    val pastTypes: List<Any?>? = null,

    @field:SerializedName("sprites")
    val sprites: Sprites? = null,

    @field:SerializedName("abilities")
    val abilities: List<AbilitiesItem?>? = null,

    @field:SerializedName("game_indices")
    val gameIndices: List<GameIndicesItem?>? = null,

    @field:SerializedName("species")
    val species: Species? = null,

    @field:SerializedName("stats")
    val stats: List<StatsItem> = listOf(),

    @field:SerializedName("moves")
    val moves: List<MovesItem?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("forms")
    val forms: List<FormsItem?>? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("order")
    val order: Int? = null
)