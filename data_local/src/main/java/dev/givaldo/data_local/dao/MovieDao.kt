package dev.givaldo.data_local.dao

import androidx.room.*
import dev.givaldo.data_local.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query(GET_ALL_QUERY)
    fun getAll(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>): List<Long>

    @Delete
    fun deleteAll(movies: List<MovieEntity>)

    @Query(GET_ALL_MOVIES_WITH_QUERY)
    fun getMoviesByQuery(query: String): Flow<List<MovieEntity>>

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${MovieEntity.TABLE_NAME}"
        private const val GET_ALL_MOVIES_WITH_QUERY =
            "SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE title LIKE :query"
    }

}

