package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.RubySapphire

data class GenerationIii(

    @field:SerializedName("firered-leafgreen")
	val fireredLeafgreen: FireredLeafgreen? = null,

    @field:SerializedName("ruby-sapphire")
	val rubySapphire: RubySapphire? = null,

    @field:SerializedName("emerald")
	val emerald: Emerald? = null
)