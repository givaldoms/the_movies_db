package dev.givaldo.data_local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.model.entity.GenreMovieCrossRef
import dev.givaldo.data_local.model.entity.MovieEntity

data class GenreWithMovies(

    @Embedded val genre: GenreEntity,

    @Relation(
        parentColumn = "genreId",
        entity = MovieEntity::class,
        entityColumn = "movieId",
        associateBy = Junction(
            GenreMovieCrossRef::class
        )
    )
    val movies: List<MovieEntity>
)