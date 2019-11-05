package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.data_remote.mapper.GenreMapper
import dev.givaldo.data_remote.mapper.MovieMapper
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@FlowPreview
class MovieRemoteDataSourceImpl(
    private val movieWebService: MovieWebService
) : MovieRemoteDataSource {

    override fun getMovies(genreId: Long, page: Int): Flow<List<Movie>> = flow {
        emit(MovieMapper.toDomain(movieWebService.getMovieList(genreId, page).movies))
    }

    override fun getMovies(query: String, page: Int): Flow<List<Movie>> = flow {
        emit(MovieMapper.toDomain(movieWebService.getMovieSearchList(query, page).movies))
    }

    override fun getGenres(): Flow<List<Genre>> = flow {
        emit(GenreMapper.toDomain(movieWebService.getGenreList().genres))
    }
}