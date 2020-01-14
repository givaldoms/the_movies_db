package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.domain.model.Movie
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun getMoviesByGenre(genreId: Long, page: Int): Flow<List<Movie>> = flow {
        //        return remote.getMovies(genreId, page)

        local.getMoviesByGenre(genreId).distinctUntilChanged()
            .onEach {
                emit(it)
            }
            .flatMapConcat {
                remote.getMovies(genreId, page).flatMapConcat {
                    local.saveMovies(it)
                }
            }
            .collect()

    }

    override fun getMoviesQuery(query: String, page: Int): Flow<List<Movie>> {
        return remote.getMovies(query, page)
    }

}