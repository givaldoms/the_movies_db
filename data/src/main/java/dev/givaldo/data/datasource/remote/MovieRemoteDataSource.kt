package dev.givaldo.data.datasource.remote

import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
interface MovieRemoteDataSource {

    fun getMovies(genreId: Long, page: Int): Flow<List<Movie>>

    fun getMovies(query: String, page: Int): Flow<List<Movie>>

    fun getGenres(): Flow<List<Genre>>

}