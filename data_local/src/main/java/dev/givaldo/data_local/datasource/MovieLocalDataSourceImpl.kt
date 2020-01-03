package dev.givaldo.data_local.datasource

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.dao.MovieDao
import dev.givaldo.data_local.mapper.GenreMapper
import dev.givaldo.data_local.mapper.MovieMapper
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao
) : MovieLocalDataSource {

    override fun getMovies(genreId: Long, query: String?): Flow<List<Movie>> {
        return movieDao.getAll().map { MovieMapper.toDomain(it) }
    }

    override fun saveMovies(list: List<Movie>): Flow<List<Movie>> {
        movieDao.insertAll(list.map { MovieMapper.fromDomain(it) })
        return flow { emit(list) }
    }

    override fun getGenres(): Flow<List<Genre>> {
        return genreDao.getAll().map {
            GenreMapper.toDomain(it)
        }
    }

    @ExperimentalCoroutinesApi
    override fun saveGenres(list: List<Genre>) = flow {
        genreDao.insertAll(GenreMapper.fromDomain(list))
        emit(list)
    }
}