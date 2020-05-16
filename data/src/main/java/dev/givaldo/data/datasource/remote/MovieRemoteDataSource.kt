package dev.givaldo.data.datasource.remote

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {

    fun getMoviesByGenre(genreId: Long, page: Int): Flow<List<Movie>>

    fun getMoviesByQuery(query: String, page: Int): Flow<List<Movie>>

}