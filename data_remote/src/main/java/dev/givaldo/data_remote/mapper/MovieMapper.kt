package dev.givaldo.data_remote.mapper

import dev.givaldo.data_remote.model.MovieResponse
import dev.givaldo.domain.model.Movie

object MovieMapper : DataRemoteMapper<MovieResponse, Movie>() {

    override fun toDomain(data: MovieResponse): Movie {
        return Movie(
            title = data.originalTitle ?: data.title ?: "",
            posterUrl = data.posterPath ?: ""
        )
    }

}