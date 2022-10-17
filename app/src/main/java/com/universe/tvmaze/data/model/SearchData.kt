package com.universe.tvmaze.data.model

import com.google.gson.annotations.SerializedName

data class SearchItemData(

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("show")
	val show: ShowData? = null
)

