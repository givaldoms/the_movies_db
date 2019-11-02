package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.exception.MissingParamsException
import dev.givaldo.domain.model.Category
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.FlowPreview

@FlowPreview
class GetMovies(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, GetMovies.Params>() {

    override suspend fun invoke(params: Params?) = when (params) {
         null -> throw MissingParamsException()
         else -> movieRepository.getMovies(
             params.category.id,
             params.query
         )
     }

    data class Params(
        val query: String,
        val category: Category
    )

}