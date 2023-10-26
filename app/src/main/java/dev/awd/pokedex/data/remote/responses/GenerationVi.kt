package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.XY

data class GenerationVi(

	@field:SerializedName("omegaruby-alphasapphire")
	val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,

	@field:SerializedName("x-y")
	val xY: XY? = null
)