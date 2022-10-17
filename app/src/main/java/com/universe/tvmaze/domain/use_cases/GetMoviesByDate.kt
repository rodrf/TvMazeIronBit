package com.universe.tvmaze.domain.use_cases

import com.universe.tvmaze.data.TvMazeRepository
import com.universe.tvmaze.domain.model.ScheduleItemDomain
import javax.inject.Inject

class GetMoviesByDate @Inject constructor(private val repository: TvMazeRepository) {
    suspend operator fun invoke(date: String): MutableList<ScheduleItemDomain> {
        val movies = repository.getBySchedule(date)
        return movies.ifEmpty {
            mutableListOf()
        }
    }
}