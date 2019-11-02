package dev.givaldo.data_local.mapper

import dev.givaldo.data_local.model.GenreEntity
import dev.givaldo.domain.model.Genre

object GenreMapper : DataLocalMapper<GenreEntity, Genre>() {

    override fun toDomain(remote: GenreEntity): Genre {
        return Genre(
            id = remote.id,
            title = remote.title
        )
    }

    override fun fromDomain(domain: Genre): GenreEntity {
        return GenreEntity(
            id = domain.id,
            title = domain.title
        )
    }
}