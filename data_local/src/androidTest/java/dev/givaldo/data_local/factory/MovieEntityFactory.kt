package dev.givaldo.data_local.factory

import dev.givaldo.data_local.model.entity.MovieEntity

object MovieEntityFactory : EntityFactory<MovieEntity> {

    override fun dumbInstance(): MovieEntity {
        return MovieEntity(
            movieId = PrimitiveDataFactory.makeDumbLong(),
            title = PrimitiveDataFactory.makeDumbString(),
            description = PrimitiveDataFactory.makeDumbString(),
            posterPath = PrimitiveDataFactory.makeDumbString()
        )
    }
}

