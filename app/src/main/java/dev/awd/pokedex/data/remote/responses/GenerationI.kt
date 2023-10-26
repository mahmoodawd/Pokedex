package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.Yellow

data class GenerationI(

    @field:SerializedName("yellow")
	val yellow: Yellow? = null,

    @field:SerializedName("red-blue")
	val redBlue: RedBlue? = null
)