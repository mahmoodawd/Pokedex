package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RubySapphire(

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)