package dev.givaldo.data.repository

import dev.givaldo.data.datasource.MovieDataSource
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
class MovieRepositoryImpl(
    private val movieDataSource: MovieDataSource
) : MovieRepository{

    override suspend fun getMovies(genreId: Int, query: String): Flow<List<Movie>> {
        return movieDataSource.getMovies(
            genreId, query, 0
        )
    }
}