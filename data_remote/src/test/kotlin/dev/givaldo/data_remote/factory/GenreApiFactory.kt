package dev.givaldo.data_remote.factory

import dev.givaldo.data_local.factory.PrimitiveDataFactory
import dev.givaldo.data_remote.model.GenreApi

object GenreApiFactory : ResponseFactory<GenreApi> {

    override fun dumbInstance(): GenreApi {
        return GenreApi(
            id = PrimitiveDataFactory.makeDumbLong(),
            title = PrimitiveDataFactory.makeDumbString()
        )

    }
}