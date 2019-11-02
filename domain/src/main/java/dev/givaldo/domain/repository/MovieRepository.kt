package dev.givaldo.domain.repository

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
interface MovieRepository {

    suspend fun getMovies(
        genreId: Int,
        query: String = ""
    ): Flow<List<Movie>>

}