package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.exception.DomainException
import dev.givaldo.domain.exception.MissingParamsException
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, GetMovies.Params>() {

    override fun run(params: Params?): Flow<List<Movie>> = when {
        params == null -> throw MissingParamsException()
        params.genre != null -> movieRepository.getMoviesByGenre(params.genre.id, params.page)
        params.query != null && params.query.isNotBlank() -> movieRepository.getMoviesQuery(
            params.query,
            params.page
        )
        else -> throw DomainException("Necessário passar id do genero ou query para busca.")
    }

    data class Params(
        val query: String? = null,
        val genre: Genre? = null,
        val page: Int = 1
    )
}