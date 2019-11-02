package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.data_remote.mapper.MovieMapper
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@FlowPreview
class MovieDataSourceImpl (
    private val movieWebService: MovieWebService
): MovieRemoteDataSource {

    override fun getMovies(
        genreId: Long,
        query: String?,
        page: Int
    ): Flow<List<Movie>> = flow {
        if (query == null) {
            emit(movieWebService.getMovieList(genreId, page).map { MovieMapper.toDomain(it) })
        } else {
            emit(movieWebService.getMovieSearchList(query, page).map { MovieMapper.toDomain(it) })
        }

    }

    override fun getGenres(): Flow<List<Genre>> = flow {
         emit(movieWebService.getGenreList())
    }
}