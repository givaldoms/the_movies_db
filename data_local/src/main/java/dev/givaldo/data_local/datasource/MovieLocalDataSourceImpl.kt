package dev.givaldo.data_local.datasource

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.dao.MovieDao
import dev.givaldo.data_local.mapper.GenreMapper
import dev.givaldo.data_local.mapper.MovieMapper
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@FlowPreview
class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao
) : MovieLocalDataSource {

    override suspend fun getMovies(genreId: Int, query: String?, page: Int): Flow<List<Movie>> {
        return movieDao.getAll().map {
            it.map { movie -> MovieMapper.toDomain(movie) }
        }
    }

    override suspend fun saveMovies(list: List<Movie>): Flow<List<Movie>> {
        movieDao.insertAll(list.map { MovieMapper.fromDomain(it) })
        return flow { emit(list) }
    }

    override suspend fun getGenres(): Flow<List<Genre>> {
        return genreDao.getAll().map {
            it.map { genre -> GenreMapper.toDomain(genre) }
        }
    }

    override suspend fun saveGenres(list: List<Genre>): Flow<List<Genre>> {
        genreDao.insertAll(list.map { GenreMapper.fromDomain(it) })
        return flow { emit(list) }
    }
}