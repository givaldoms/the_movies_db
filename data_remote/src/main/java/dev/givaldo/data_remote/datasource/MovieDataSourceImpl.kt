package dev.givaldo.data_remote.datasource

import dev.givaldo.data_remote.model.Genre
import dev.givaldo.data_remote.model.Movie
import dev.givaldo.data_remote.service.MovieWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class MovieDataSourceImpl (
    private val movieWebService: MovieWebService
) {

    @FlowPreview
    fun getMovies(
        genre: Genre,
        query: String? = null,
        page: Int
    ): Flow<List<Movie>> = flow {

        if (query == null) {
            emit(movieWebService.getMovieList(genre.id, page))
        } else {
            emit(movieWebService.getMovieSearchList(query, page))
        }

    }.flowOn(Dispatchers.IO)

}