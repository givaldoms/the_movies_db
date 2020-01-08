package dev.givaldo.data_local.factory

import dev.givaldo.data_local.model.entity.GenreEntity

object GenreEntityFactory : EntityFactory<GenreEntity> {

    override fun dumbInstance(): GenreEntity {
        return GenreEntity(
            genreId = PrimitiveDataFactory.makeDumbLong(),
            title = PrimitiveDataFactory.makeDumbString()
        )
    }

}