package dev.awd.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.Stat

data class StatsItem(

    @field:SerializedName("stat")
	val stat: Stat? = null,

    @field:SerializedName("base_stat")
	val baseStat: Int? = null,

    @field:SerializedName("effort")
	val effort: Int? = null
)