package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName
import dev.awd.pokedex.data.remote.responses.VersionDetailsItem

data class HeldItemsItem(

	@field:SerializedName("item")
	val item: Item? = null,

	@field:SerializedName("version_details")
	val versionDetails: List<VersionDetailsItem?>? = null
)