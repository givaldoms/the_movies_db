package dev.givaldo.data_local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GenreWithMovies(
    @Embedded val Genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(GenreMovieCrossRef::class)
    )
    val movies: List<MovieEntity>
)