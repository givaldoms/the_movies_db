package dev.givaldo.data_local.factory

import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.model.entity.GenreMovieCrossRef
import dev.givaldo.data_local.model.entity.MovieEntity

object GenreMovieCrossRefFactory : EntityFactory<GenreMovieCrossRef> {

    override fun dumbInstance(): GenreMovieCrossRef {
        return GenreMovieCrossRef(
            movieId = PrimitiveDataFactory.makeDumbLong(),
            genreId = PrimitiveDataFactory.makeDumbLong()
        )
    }

    fun makeDumbList(
        genre: GenreEntity,
        movies: List<MovieEntity>
    ): List<GenreMovieCrossRef> {
        return movies.map {
            GenreMovieCrossRef(
                movieId = it.movieId,
                genreId = genre.genreId
            )
        }

    }
}