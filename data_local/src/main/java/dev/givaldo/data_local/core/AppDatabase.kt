package dev.givaldo.data_local.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.givaldo.data_local.core.LocalConstants.DATABASE_NAME
import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.dao.GenreMovieCrossRefDao
import dev.givaldo.data_local.dao.MovieDao
import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.model.entity.GenreMovieCrossRef
import dev.givaldo.data_local.model.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, GenreEntity::class, GenreMovieCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun genreMovieCressRefDao(): GenreMovieCrossRefDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}