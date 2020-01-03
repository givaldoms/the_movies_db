package dev.givaldo.di

import dev.givaldo.presentation.model.GenreBinding
import dev.givaldo.presentation.viewmodel.MainViewModel
import dev.givaldo.presentation.viewmodel.MoviesViewModel
import dev.givaldo.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel { (genre: GenreBinding) ->
        MoviesViewModel(genre)
    }

    viewModel {
        SearchViewModel()
    }

}