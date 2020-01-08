package dev.givaldo.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.givaldo.data_local.model.GenreWithMovies
import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.model.entity.GenreMovieCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenreWithMovies(vararg items: GenreMovieCrossRef): List<Long>

    @Query(GET_ALL_QUERY)
    fun getGenreWithMovies(): Flow<List<GenreWithMovies>>

    companion object {
        private const val GET_ALL_QUERY = "SELECT * FROM ${GenreEntity.TABLE_NAME}"
    }
}