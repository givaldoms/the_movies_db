package dev.givaldo.data_remote.mapper

import dev.givaldo.data_remote.constants.ApiConstants.BASE_IMAGE_URL
import dev.givaldo.data_remote.model.MovieApi
import dev.givaldo.domain.model.Movie

object MovieMapper : DataRemoteMapper<MovieApi, Movie> {

    override fun toDomain(remote: MovieApi): Movie {
        return Movie(
            id = remote.id,
            title = remote.title ?: remote.originalTitle ?: "",
            posterUrl = "$BASE_IMAGE_URL${remote.posterPath}",
            description = remote.description ?: ""
        )
    }

}