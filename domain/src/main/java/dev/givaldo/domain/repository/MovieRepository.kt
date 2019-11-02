package dev.givaldo.domain.repository

import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
interface MovieRepository {

    suspend fun getMovies(
        genreId: Long,
        query: String = ""
    ): Flow<List<Movie>>

    suspend fun getGenres(): Flow<List<Genre>>

}