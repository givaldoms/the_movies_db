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

    @Query(GET_ALL_QUERY)
    fun getGenreWithMovies(): Flow<List<GenreWithMovies>>

    @Query(GET_ALL_FROM_A_GENRE)
    fun getMoviesWithGenre(genreId: Long): Flow<GenreWithMovies>

    @Transaction
    fun getMoviesByGenre(genreId: Long): Flow<List<MovieEntity>> = getMoviesWithGenre(genreId)
        .map {
            it.movies
        }

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${GenreEntity.TABLE_NAME}"
        private const val GET_ALL_FROM_A_GENRE =
            "SELECT * FROM ${GenreEntity.TABLE_NAME} WHERE genreId = :genreId LIMIT 1"
    }
}