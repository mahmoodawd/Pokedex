package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Crystal(

	@field:SerializedName("back_transparent")
	val backTransparent: String? = null,

	@field:SerializedName("back_shiny_transparent")
	val backShinyTransparent: String? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@field:SerializedName("front_shiny_transparent")
	val frontShinyTransparent: String? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)