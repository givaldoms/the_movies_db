package dev.givaldo.data.datasource.local

import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMovies(
        genreId: Long,
        query: String? = null
    ): Flow<List<Movie>>

    fun saveMovies(list: List<Movie>): Flow<List<Movie>>

    fun getGenres(): Flow<List<Genre>>

    fun saveGenres(list: List<Genre>): Flow<List<Genre>>
}
