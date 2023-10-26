package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.Version

data class VersionDetailsItem(

    @field:SerializedName("version")
	val version: Version? = null,

    @field:SerializedName("rarity")
	val rarity: Int? = null
)