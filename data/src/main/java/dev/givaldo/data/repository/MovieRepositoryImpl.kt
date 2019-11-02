package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.concatenate
import kotlinx.coroutines.flow.onEach

@FlowPreview
class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository{

    override suspend fun getMovies(genreId: Long, query: String): Flow<List<Movie>> {
        return remote.getMovies(
            genreId, query, 0
        )
    }

    override suspend fun getGenres(): Flow<List<Genre>> {
        remote.getGenres().onEach {
            local.saveGenres(it)
        }

        return local.getGenres()
    }
}