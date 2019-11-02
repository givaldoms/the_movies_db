package dev.givaldo.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.givaldo.data_local.core.TablesName.GENRE_TABLE_NAME
import dev.givaldo.data_local.model.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query(GET_ALL_QUERY)
    fun getAll(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<GenreEntity>)

}


private const val GET_ALL_QUERY = "SELECT * FROM $GENRE_TABLE_NAME"

