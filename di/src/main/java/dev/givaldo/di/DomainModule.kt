package dev.givaldo.di

import dev.givaldo.domain.interactor.genres.GetGenres
import dev.givaldo.domain.interactor.movie.GetMoviesByGenre
import dev.givaldo.domain.interactor.movie.GetMoviesByQuery
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetGenres(genreRepository = get())
    }

    factory {
        GetMoviesByQuery(movieRepository = get())
    }

    factory {
        GetMoviesByGenre(movieRepository = get())
    }
}