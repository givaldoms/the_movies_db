package dev.givaldo.data_remote.factory

import dev.givaldo.data_remote.model.GenresResponse

object GenreResponseFactory : ResponseFactory<GenresResponse> {

    override fun dumbInstance(): GenresResponse {
        return GenresResponse(
            genres = GenreApiFactory.makeDumbList()
        )
    }
}