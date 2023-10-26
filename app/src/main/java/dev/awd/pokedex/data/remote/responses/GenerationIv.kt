package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.DiamondPearl

data class GenerationIv(

    @field:SerializedName("platinum")
	val platinum: Platinum? = null,

    @field:SerializedName("diamond-pearl")
	val diamondPearl: DiamondPearl? = null,

    @field:SerializedName("heartgold-soulsilver")
	val heartgoldSoulsilver: HeartgoldSoulsilver? = null
)