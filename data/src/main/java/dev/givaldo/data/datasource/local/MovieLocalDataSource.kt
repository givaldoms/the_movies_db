package dev.givaldo.data.datasource.local

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMoviesByGenre(genreId: Long): Flow<List<Movie>>

    fun getMoviesByQuery(query: String): Flow<List<Movie>>

    fun saveMovies(list: List<Movie>): Flow<List<Movie>>

}
