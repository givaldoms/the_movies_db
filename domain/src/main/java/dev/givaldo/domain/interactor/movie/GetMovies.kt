package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.exception.MissingParamsException
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOn

@FlowPreview
class GetMovies(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, GetMovies.Params>() {

    override suspend fun invoke(params: Params?) = when (params) {
         null -> throw MissingParamsException()
         else -> movieRepository.getMovies(
             params.genre.id,
             params.query
         ).flowOn(Dispatchers.IO)
     }

    data class Params(
        val query: String,
        val genre: Genre
    )

}