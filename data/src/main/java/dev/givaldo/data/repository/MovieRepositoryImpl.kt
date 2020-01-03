package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    override fun getMovies(genreId: Long, page: Int): Flow<List<Movie>> {
        return remote.getMovies(genreId, page)
    }

    override fun getMovies(query: String, page: Int): Flow<List<Movie>> {
        return remote.getMovies(query, page)
    }

    override fun getGenres(): Flow<List<Genre>> = flow {
        val remote = genreRemoteDataSource.getGenres().single()
        local.saveGenres(remote).single()

        local.getGenres().collect {
            emit(it)
        }

    }
}