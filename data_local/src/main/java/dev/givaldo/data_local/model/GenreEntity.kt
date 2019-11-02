package dev.givaldo.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.givaldo.data_local.core.TablesName.GENRE_TABLE_NAME

@Entity(tableName = GENRE_TABLE_NAME)
data class GenreEntity(
    @PrimaryKey val id: Long,
    val title: String
)