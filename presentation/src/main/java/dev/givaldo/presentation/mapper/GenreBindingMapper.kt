package dev.givaldo.presentation.mapper

import dev.givaldo.domain.model.Genre
import dev.givaldo.presentation.model.GenreBinding

object GenreBindingMapper : PresentationMapper<GenreBinding, Genre> {

    override fun fromDomain(domain: Genre): GenreBinding {
        return GenreBinding(
            id = domain.id,
            title = domain.title
        )
    }

    override fun toDomain(presentation: GenreBinding): Genre {
        return Genre(
            id = presentation.id,
            title = presentation.title
        )
    }

}