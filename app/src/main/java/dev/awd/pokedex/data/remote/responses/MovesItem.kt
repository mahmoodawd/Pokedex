package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.VersionGroupDetailsItem

data class MovesItem(

    @field:SerializedName("version_group_details")
	val versionGroupDetails: List<VersionGroupDetailsItem?>? = null,

    @field:SerializedName("move")
	val move: Move? = null
)