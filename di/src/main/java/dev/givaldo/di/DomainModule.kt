package dev.givaldo.di

import dev.givaldo.domain.interactor.movie.GetGenres
import dev.givaldo.domain.interactor.movie.GetMovies
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val domainModule = module {

    factory {
        GetGenres(movieRepository = get())
    }

    factory {
        GetMovies(movieRepository = get())
    }

}