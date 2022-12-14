package com.universe.tvmaze.domain.use_cases

import com.universe.tvmaze.data.TvMazeRepository
import com.universe.tvmaze.domain.model.ScheduleItemDomain
import com.universe.tvmaze.domain.model.SearchItemDomain
import javax.inject.Inject

/**
 *
 * @property repository To know if we need to retrieve data from Internet or DataBase
 */
class GetMoviesByParam @Inject constructor(private val repository: TvMazeRepository) {
    suspend operator fun invoke(param: String): MutableList<ScheduleItemDomain> {
        val movies = repository.getByQuery(param)
        return movies.ifEmpty {
            mutableListOf()
        }
    }
}