package dev.givaldo.data_remote.mapper

import dev.givaldo.data_remote.model.MovieResponse
import dev.givaldo.domain.model.Movie

object MovieMapper : DataRemoteMapper<MovieResponse, Movie>() {

    override fun toDomain(remote: MovieResponse): Movie {
        return Movie(
            id = remote.id,
            title = remote.originalTitle ?: remote.title ?: "",
            posterUrl = remote.posterPath ?: "",
            description = remote.description ?: ""
        )
    }

}