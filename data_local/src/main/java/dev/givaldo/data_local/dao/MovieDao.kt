package dev.givaldo.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.givaldo.data_local.dao.MovieDaoConstants.GET_ALL_QUERY
import dev.givaldo.data_local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query(GET_ALL_QUERY)
    fun getAll(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<MovieEntity>)

}

object MovieDaoConstants {

    const val GET_ALL_QUERY = "SELECT * FROM movie"

}