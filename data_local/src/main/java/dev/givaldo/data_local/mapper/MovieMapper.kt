package dev.givaldo.data_local.mapper

import dev.givaldo.data_local.model.MovieEntity
import dev.givaldo.domain.model.Movie

object MovieMapper : DataLocalMapper<MovieEntity, Movie>() {

    override fun toDomain(remote: MovieEntity): Movie {
        return Movie(
            id = remote.id,
            title = remote.title,
            description = remote.description,
            posterUrl = remote.posterPath
        )

    }

    override fun fromDomain(domain: Movie): MovieEntity {
        return MovieEntity(
            id = domain.id,
            title = domain.title,
            posterPath = domain.posterUrl,
            description = domain.description
        )

    }

}