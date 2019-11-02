package dev.givaldo.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    val description: String,
    val posterPath: String
)