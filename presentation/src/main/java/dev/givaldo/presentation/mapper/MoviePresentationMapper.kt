package dev.givaldo.presentation.mapper

import dev.givaldo.domain.model.Movie
import dev.givaldo.presentation.model.MovieBinding

object MoviePresentationMapper : PresentationMapper<MovieBinding, Movie> {

    override fun fromDomain(domain: Movie): MovieBinding {
        return MovieBinding(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            posterUrl = domain.posterUrl
        )
    }

    override fun toDomain(presentation: MovieBinding): Movie {
        return Movie(
            id = presentation.id,
            posterUrl = presentation.posterUrl,
            description = presentation.description,
            title = presentation.title
        )
    }
}