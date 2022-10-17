package com.universe.tvmaze.data

import com.universe.tvmaze.data.network.TvMazeService
import com.universe.tvmaze.domain.model.*
import com.universe.tvmaze.domain.repository.ITvMazeRepository
import javax.inject.Inject

class TvMazeRepository @Inject constructor(
    private val api: TvMazeService
) : ITvMazeRepository {
    override suspend fun getBySchedule(date: String): MutableList<ScheduleItemDomain> {
        return api.getMoviesByDate(date)
    }

    override suspend fun getByQuery(param: String): MutableList<ScheduleItemDomain> {
        return api.getMoviesByParam(param)
    }

    override suspend fun getDetail(idMovie: Long): DetailDomain? {
        return api.getDetail(idMovie)
    }

    override suspend fun getCast(idMovie: Long): MutableList<DetailCastItemDomain>? {
        return api.getCast(idMovie)
    }
}