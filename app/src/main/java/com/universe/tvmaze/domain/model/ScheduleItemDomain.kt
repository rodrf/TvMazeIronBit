package com.universe.tvmaze.domain.model

import com.google.gson.annotations.SerializedName
import com.universe.tvmaze.data.model.ScheduleData
import com.universe.tvmaze.data.model.ScheduleItemData
import com.universe.tvmaze.data.model.ShowData


data class ScheduleItemDomain(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("show")
	val show: ShowDomain? = null,

	@field:SerializedName("airdate")
	val airdate: String? = null,

	@field:SerializedName("airtime")
	val airtime: String? = null,

	@field:SerializedName("rating")
	val rating: RatingDomain? = null,
)

data class NetworkDomain(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)

data class ShowDomain(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("network")
	val network: NetworkDomain? = null,

	@field:SerializedName("image")
	val image: ImageDomain? = null,

	@field:SerializedName("rating")
	val rating: RatingDomain? = null,

	@field:SerializedName("schedule")
	val schedule: ScheduleDomain? = null,
	)
data class ScheduleDomain(

	@field:SerializedName("days")
	val days: MutableList<String>? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class RatingDomain(

	@field:SerializedName("average")
	val average: Any? = null
)

data class ImageDomain(

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)
