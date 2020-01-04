package dev.givaldo.di

import dev.givaldo.domain.interactor.movie.GetGenres
import dev.givaldo.domain.interactor.movie.GetMovies
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetGenres(genreRepository = get())
    }

    factory {
        GetMovies(movieRepository = get())
    }

}