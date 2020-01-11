package dev.givaldo.data_local.dao

import androidx.room.*
import dev.givaldo.data_local.model.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GenreDao {

    @Query(GET_ALL_QUERY)
    abstract fun getAll(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(genres: GenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(genres: List<GenreEntity>): List<Long>

    @Delete
    abstract suspend fun deleteAll(genres: List<GenreEntity>)

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${GenreEntity.TABLE_NAME}"
    }

}


