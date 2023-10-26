package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class XY(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)