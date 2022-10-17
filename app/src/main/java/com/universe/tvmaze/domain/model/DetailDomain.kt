package com.universe.tvmaze.domain.model

import com.google.gson.annotations.SerializedName
import com.universe.tvmaze.data.model.ImageData
import com.universe.tvmaze.data.model.NetworkData
import com.universe.tvmaze.data.model.RatingData
import com.universe.tvmaze.data.model.ScheduleData

data class DetailDomain(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image")
	val image: ImageDomain? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("network")
	val network: NetworkDomain? = null,

	@field:SerializedName("rating")
	val rating: RatingDomain? = null,

	@field:SerializedName("officialSite")
	val officialSite: String? = null,

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("genres")
	val genres: MutableList<String?>? = null,

	@field:SerializedName("schedule")
	val schedule: ScheduleDomain? = null
)

