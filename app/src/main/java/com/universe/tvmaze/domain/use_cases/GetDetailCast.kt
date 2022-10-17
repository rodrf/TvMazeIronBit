package com.universe.tvmaze.domain.use_cases

import com.universe.tvmaze.data.TvMazeRepository
import com.universe.tvmaze.domain.model.DetailCastDomain
import com.universe.tvmaze.domain.model.DetailCastItemDomain
import com.universe.tvmaze.domain.model.DetailDomain
import javax.inject.Inject

class GetDetailCast @Inject constructor(private val repository: TvMazeRepository) {
    suspend operator fun invoke(idMovie: Long): MutableList<DetailCastItemDomain>? {
        val movies = repository.getCast(idMovie)
        return movies
    }
}