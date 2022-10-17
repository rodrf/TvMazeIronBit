package com.universe.tvmaze.domain.repository

import com.universe.tvmaze.domain.model.*

interface ITvMazeRepository {
    suspend fun getBySchedule(date: String) : MutableList<ScheduleItemDomain>
    suspend fun getByQuery(param: String):MutableList<ScheduleItemDomain>
    suspend fun getDetail(idMovie: Long): DetailDomain?
    suspend fun getCast(idMovie: Long): MutableList<DetailCastItemDomain>?
}