package dev.awd.pokedex.data.remote.responses

import com.example.jetpackcomposepokedex.data.remote.responses.GenerationI
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationIi
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationIii
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationIv
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationV
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationVi
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationVii
import com.example.jetpackcomposepokedex.data.remote.responses.GenerationViii
import com.google.gson.annotations.SerializedName

data class Versions(

	@field:SerializedName("generation-iii")
	val generationIii: GenerationIii? = null,

	@field:SerializedName("generation-ii")
	val generationIi: GenerationIi? = null,

	@field:SerializedName("generation-v")
	val generationV: GenerationV? = null,

	@field:SerializedName("generation-iv")
	val generationIv: GenerationIv? = null,

	@field:SerializedName("generation-vii")
	val generationVii: GenerationVii? = null,

	@field:SerializedName("generation-i")
	val generationI: GenerationI? = null,

	@field:SerializedName("generation-viii")
	val generationViii: GenerationViii? = null,

	@field:SerializedName("generation-vi")
	val generationVi: GenerationVi? = null
)