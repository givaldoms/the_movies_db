package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.MovieDataSource
import dev.givaldo.data_remote.mapper.MovieMapper
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@FlowPreview
class MovieDataSourceImpl (
    private val movieWebService: MovieWebService
): MovieDataSource {

    override fun getMovies(
        genreId: Int,
        query: String?,
        page: Int
    ): Flow<List<Movie>> = flow {
        if (query == null) {
            emit(movieWebService.getMovieList(genreId, page).map { MovieMapper.toDomain(it) })
        } else {
            emit(movieWebService.getMovieSearchList(query, page).map { MovieMapper.toDomain(it) })
        }

    }

}