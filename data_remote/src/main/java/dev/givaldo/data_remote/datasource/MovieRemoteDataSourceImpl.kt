package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.data_remote.mapper.MovieMapper
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.data_remote.utils.flowApi
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieRemoteDataSourceImpl(
    private val movieWebService: MovieWebService
) : MovieRemoteDataSource {

    override fun getMoviesByGenre(genreId: Long, page: Int): Flow<List<Movie>> = flowApi {
        MovieMapper.toDomain(movieWebService.getMovieList(genreId, page).movies)
    }

    override fun getMoviesByQuery(query: String, page: Int): Flow<List<Movie>> = flowApi {
        MovieMapper.toDomain(movieWebService.getMovieSearchList(query, page).movies)
    }

}