package com.universe.tvmaze.data.network

import com.universe.tvmaze.data.model.*
import com.universe.tvmaze.domain.model.*

import retrofit2.Response
import retrofit2.http.*

interface TvMazeApiClient {

    @GET("schedule")
    suspend fun searchByDate(@QueryMap filterData: Map<String, String>): Response<MutableList<ScheduleItemDomain>>

    @GET("search/shows")
    suspend fun searchByParam(@Query("q") q: String): Response<MutableList<ScheduleItemDomain>>

    @GET("shows/{id_show}")
    suspend fun getDetail(@Path("id_show") idShow: Long): Response<DetailDomain>

    @GET("shows/{id_show}/cast")
    suspend fun getCast(@Path("id_show") idShow: Long): Response<MutableList<DetailCastItemDomain>>


}