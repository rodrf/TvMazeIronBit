package com.universe.tvmaze.data.model

import com.google.gson.annotations.SerializedName


data class ScheduleItemData(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("show")
	val show: ShowData? = null,

	@field:SerializedName("airdate")
	val airdate: String? = null,

	@field:SerializedName("airtime")
	val airtime: String? = null,

	@field:SerializedName("rating")
	val rating: RatingData? = null,
)
data class NetworkData(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)

data class ShowData(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("network")
	val network: NetworkData? = null,

	@field:SerializedName("image")
	val image: ImageData? = null,

	@field:SerializedName("rating")
	val rating: RatingData? = null,

	@field:SerializedName("schedule")
	val schedule: ScheduleData? = null,

)
data class ScheduleData(
	@field:SerializedName("days")
	val days: MutableList<String>? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class RatingData(

	@field:SerializedName("average")
	val average: Any? = null
)

data class ImageData(

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)
