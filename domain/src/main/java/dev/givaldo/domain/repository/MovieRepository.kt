package dev.givaldo.domain.repository

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesByGenre(
        genreId: Long,
        page: Int
    ): Flow<List<Movie>>

    fun getMoviesQuery(
        query: String,
        page: Int
    ): Flow<List<Movie>>

}