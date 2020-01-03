package dev.givaldo.data_local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import dev.givaldo.data_local.model.GenreMovieCrossRef.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [MovieEntity.ID, GenreEntity.ID],
    indices = [Index(MovieEntity.ID, GenreEntity.ID)]
)
data class GenreMovieCrossRef(

    @ColumnInfo(name = MovieEntity.ID)
    val movieId: Long,

    @ColumnInfo(name = GenreEntity.ID)
    val genreId: Long
) {

    companion object {

        const val TABLE_NAME = "genre_with_movies"

    }

}