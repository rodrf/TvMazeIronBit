package com.universe.tvmaze.domain.use_cases

import com.universe.tvmaze.data.TvMazeRepository
import com.universe.tvmaze.domain.model.DetailDomain
import javax.inject.Inject

class GetDetailMovie @Inject constructor(private val repository: TvMazeRepository) {
    suspend operator fun invoke(idMovie: Long): DetailDomain? {
        val movies = repository.getDetail(idMovie)
        return movies
    }
}