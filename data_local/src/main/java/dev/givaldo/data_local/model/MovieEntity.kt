package dev.givaldo.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.givaldo.data_local.core.TablesName.MOVIE_TABLE_NAME

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    val description: String,
    val posterPath: String
)