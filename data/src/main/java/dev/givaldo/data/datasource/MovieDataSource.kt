package dev.givaldo.data.datasource

import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
interface MovieDataSource {

    fun getMovies(genreId: Int,
                  query: String? = null,
                  page: Int): Flow<List<Movie>>

}