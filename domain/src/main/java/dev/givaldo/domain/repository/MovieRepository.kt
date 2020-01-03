package dev.givaldo.domain.repository

import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(
        genreId: Long,
        page: Int
    ): Flow<List<Movie>>

    fun getMovies(
        query: String,
        page: Int
    ): Flow<List<Movie>>

    fun getGenres(): Flow<List<Genre>>

}