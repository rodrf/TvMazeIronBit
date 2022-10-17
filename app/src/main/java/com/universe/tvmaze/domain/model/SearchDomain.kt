package com.universe.tvmaze.domain.model

import com.google.gson.annotations.SerializedName


data class SearchItemDomain(

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("show")
	val show: ShowDomain? = null
)

