package dev.givaldo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.givaldo.domain.interactor.movie.GetGenres
import dev.givaldo.presentation.extensions.asLiveData
import dev.givaldo.presentation.mapper.GenreBindingMapper
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val getGenres: GetGenres by inject()

    val genresLiveData = getGenres().map {
        GenreBindingMapper.fromDomain(it)
    }.asLiveData()
}

