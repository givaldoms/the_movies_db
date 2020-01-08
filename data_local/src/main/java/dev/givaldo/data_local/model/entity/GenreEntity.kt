package dev.givaldo.data_local.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = GenreEntity.TABLE_NAME,
    indices = [Index("genreId")]
)
data class GenreEntity(
    @PrimaryKey(autoGenerate = false) val genreId: Long,
    val title: String
) {
    companion object {
        const val TABLE_NAME = "genre"
        const val ID = "genreId"
    }
}

