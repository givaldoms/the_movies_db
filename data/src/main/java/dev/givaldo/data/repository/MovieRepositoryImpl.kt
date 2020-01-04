package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    override fun getMovies(genreId: Long, page: Int): Flow<List<Movie>> {
        return remote.getMovies(genreId, page)
    }

    override fun getMovies(query: String, page: Int): Flow<List<Movie>> {
        return remote.getMovies(query, page)
    }

}