package dev.givaldo.data_local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = GenreEntity.TABLE_NAME,
    indices = [Index(GenreEntity.ID)]
)
data class GenreEntity(
    @PrimaryKey val genreId: Long,
    val title: String
) {
    companion object {
        const val TABLE_NAME = "genre"
        const val ID = "genreId"
    }
}

