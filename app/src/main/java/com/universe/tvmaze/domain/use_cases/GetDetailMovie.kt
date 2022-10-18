package com.universe.tvmaze.domain.use_cases

import com.universe.tvmaze.data.TvMazeRepository
import com.universe.tvmaze.domain.model.DetailDomain
import javax.inject.Inject

/**
 *
 * @property repository To know if we need to retrieve data from Internet or DataBase
 */
class GetDetailMovie @Inject constructor(private val repository: TvMazeRepository) {
    suspend operator fun invoke(idMovie: Long): DetailDomain? {
        val movies = repository.getDetail(idMovie)
        return movies
    }
}