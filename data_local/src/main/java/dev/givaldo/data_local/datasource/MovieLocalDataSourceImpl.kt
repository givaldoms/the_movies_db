package dev.givaldo.data_local.datasource

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data_local.dao.GenreMovieCrossRefDao
import dev.givaldo.data_local.dao.MovieDao
import dev.givaldo.data_local.mapper.MovieMapper
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val movieCrossRefDao: GenreMovieCrossRefDao
) : MovieLocalDataSource {

    override fun getMovies(genreId: Long): Flow<List<Movie>> {
        return movieCrossRefDao.getMoviesByGenre(genreId)
            .map { MovieMapper.toDomain(it) }
    }

    override fun saveMovies(list: List<Movie>): Flow<List<Movie>> {
        return flow {
            movieDao.insertAll(list.map { MovieMapper.fromDomain(it) })
            emit(list)
        }
    }

}