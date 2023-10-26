package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.UltraSunUltraMoon

data class GenerationVii(

	@field:SerializedName("icons")
	val icons: Icons? = null,

	@field:SerializedName("ultra-sun-ultra-moon")
	val ultraSunUltraMoon: UltraSunUltraMoon? = null
)