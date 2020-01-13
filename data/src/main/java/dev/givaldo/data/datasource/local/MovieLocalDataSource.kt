package dev.givaldo.data.datasource.local

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun getMovies(genreId: Long): Flow<List<Movie>>

    fun getMovies(query: String): Flow<List<Movie>>

    fun saveMovies(list: List<Movie>): Flow<List<Movie>>

}
