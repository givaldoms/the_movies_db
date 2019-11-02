package dev.givaldo.data.datasource.local

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
interface MovieLocalDataSource {

    suspend fun getMovies(genreId: Int,
                  query: String? = null,
                  page: Int): Flow<List<Movie>>


    suspend fun saveMovies(list: List<Movie>): Flow<List<Movie>>


}