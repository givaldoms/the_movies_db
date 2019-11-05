package dev.givaldo.data_remote.mapper

import dev.givaldo.data_remote.model.GenreApi
import dev.givaldo.domain.model.Genre

object GenreMapper : DataRemoteMapper<GenreApi, Genre> {

    override fun toDomain(remote: GenreApi): Genre {
        return Genre(
            id = remote.id,
            title = remote.title
        )
    }

}