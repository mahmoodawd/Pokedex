package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.Animated

data class BlackWhite(

    @field:SerializedName("back_shiny_female")
	val backShinyFemale: Any? = null,

    @field:SerializedName("back_female")
	val backFemale: Any? = null,

    @field:SerializedName("back_default")
	val backDefault: String? = null,

    @field:SerializedName("front_shiny_female")
	val frontShinyFemale: Any? = null,

    @field:SerializedName("front_default")
	val frontDefault: String? = null,

    @field:SerializedName("animated")
	val animated: Animated? = null,

    @field:SerializedName("front_female")
	val frontFemale: Any? = null,

    @field:SerializedName("back_shiny")
	val backShiny: String? = null,

    @field:SerializedName("front_shiny")
	val frontShiny: String? = null
)