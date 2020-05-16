package dev.givaldo.data_local.dao

import androidx.room.*
import dev.givaldo.data_local.model.GenreWithMovies
import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.model.entity.GenreMovieCrossRef
import dev.givaldo.data_local.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface GenreMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenreWithMovies(vararg items: GenreMovieCrossRef): List<Long>

    @Transaction
    @Query(GET_ALL_QUERY)
    fun getAllGenreWithMovies(): Flow<List<GenreWithMovies>>

    @Transaction
    @Query(GET_ALL_BY_GENRE)
    fun getMoviesWithGenreByGenreId(genreId: Long): Flow<GenreWithMovies>

    @Transaction
    fun getMoviesByGenreId(genreId: Long): Flow<List<MovieEntity>> =
        getMoviesWithGenreByGenreId(genreId)
            .map {
                it.movies
            }

    @Transaction
    suspend fun insertMoviesByGenres(
        movieDao: MovieDao,
        genreDao: GenreDao,
        movies: List<MovieEntity>, genres: List<GenreEntity>
    ) {

        movieDao.insertAll(movies)
        genreDao.insertAll(genres)

        val a: List<GenreMovieCrossRef> = movies.map { m ->
            genres.map { g ->
                GenreMovieCrossRef(
                    movieId = m.movieId,
                    genreId = g.genreId
                )
            }
        }.flatten()

        insertGenreWithMovies(*a.toTypedArray())
    }

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${GenreEntity.TABLE_NAME}"
        private const val GET_ALL_BY_GENRE =
            "SELECT * FROM ${GenreEntity.TABLE_NAME} WHERE genreId = :genreId LIMIT 1"
    }
}