package com.universe.tvmaze.data.network

import com.universe.tvmaze.data.model.*
import com.universe.tvmaze.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class TvMazeService @Inject constructor(private val api: TvMazeApiClient) {

    suspend fun getMoviesByDate(date: String): MutableList<ScheduleItemDomain> {
        return withContext(Dispatchers.IO) {
            try {
                val mapData = mapOf("country" to "US", "date" to date)
                val response = api.searchByDate(mapData)
                response.body() ?: mutableListOf()
            } catch (ex: Exception) {
                mutableListOf()
            }
        }
    }
    suspend fun getMoviesByParam(date: String): MutableList<ScheduleItemDomain> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.searchByParam(date)
                response.body() ?: mutableListOf()
            } catch (ex: Exception) {
                mutableListOf()
            }
        }
    }
    suspend fun getDetail(idShow: Long): DetailDomain?{
        return withContext(Dispatchers.IO){
            try {
                val response = api.getDetail(idShow)
                response.body()
            }catch (ex: Exception){
                null
            }
        }
    }
    suspend fun getCast(idShow: Long): MutableList<DetailCastItemDomain>?{
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCast(idShow)
                response.body()
            }catch (ex: Exception){
                null
            }
        }
    }


}