package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.BlackWhite

data class GenerationV(

	@field:SerializedName("black-white")
	val blackWhite: BlackWhite? = null
)