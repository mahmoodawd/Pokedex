package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.DreamWorld

data class Other(

    @field:SerializedName("dream_world")
	val dreamWorld: DreamWorld? = null,

    @field:SerializedName("official-artwork")
	val officialArtwork: OfficialArtwork? = null,

    @field:SerializedName("home")
	val home: Home? = null
)