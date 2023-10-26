package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.Crystal
import dev.awd.pokedex.data.remote.responses.Silver

data class GenerationIi(

    @field:SerializedName("gold")
	val gold: Gold? = null,

    @field:SerializedName("crystal")
	val crystal: Crystal? = null,

    @field:SerializedName("silver")
	val silver: Silver? = null
)