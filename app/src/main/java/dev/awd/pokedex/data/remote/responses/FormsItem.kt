package com.example.jetpackcomposepokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class FormsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)