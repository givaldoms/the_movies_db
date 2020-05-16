package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.exception.FieldValueException
import dev.givaldo.domain.exception.MissingParamsException
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenre(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, GetMoviesByGenre.Params>() {

    override fun run(params: Params?): Flow<List<Movie>> = when {
        params == null -> throw MissingParamsException()
        params.genre.isValid()
            .not() -> throw FieldValueException("Genre ${params.genre} is invalid")
        else -> {
            movieRepository.getMoviesByGenre(
                genreId = params.genre.id,
                page = params.page
            )
        }
    }

    data class Params(
        val genre: Genre,
        val page: Int = 1
    )

    private fun Genre.isValid(): Boolean {
        return id >= 0 || title.isNotBlank()
    }
}