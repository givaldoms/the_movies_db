package dev.givaldo.data_local.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = MovieEntity.TABLE_NAME,
    indices = [Index(MovieEntity.ID)]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val movieId: Long,
    val title: String,
    val description: String,
    val posterPath: String
) {

    companion object {
        const val ID = "movieId"
        const val TABLE_NAME = "movie"
    }

}
