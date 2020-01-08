package dev.givaldo.data_local.dao

import androidx.room.*
import dev.givaldo.data_local.model.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query(GET_ALL_QUERY)
    fun getAll(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genres: GenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<GenreEntity>): List<Long>

    @Delete
    suspend fun deleteAll(genres: List<GenreEntity>)

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${GenreEntity.TABLE_NAME}"
    }

}


