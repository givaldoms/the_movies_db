package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.exception.MissingParamsException
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByQuery(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, GetMoviesByQuery.Params>() {

    override fun run(params: Params?): Flow<List<Movie>> = when (params) {
        null -> throw MissingParamsException()
        else -> movieRepository.getMoviesQuery(
            query = params.query,
            page = params.page
        )
    }

    data class Params(
        val query: String,
        val page: Int = 1
    )
}