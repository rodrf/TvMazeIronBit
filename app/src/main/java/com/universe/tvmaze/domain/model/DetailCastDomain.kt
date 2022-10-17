package com.universe.tvmaze.domain.model

import com.google.gson.annotations.SerializedName

data class DetailCastDomain(

	@field:SerializedName("DetailCast")
	val detailCast: MutableList<DetailCastItemDomain>? = null
)

data class DetailCastItemDomain(

	@field:SerializedName("person")
	val person: PersonDomain? = null,
)

data class PersonDomain(

	@field:SerializedName("image")
	val image: ImageDomain? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)



