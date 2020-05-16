package dev.givaldo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.givaldo.domain.interactor.genres.GetGenres
import dev.givaldo.presentation.extensions.asLiveData
import dev.givaldo.presentation.mapper.GenreBindingMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val getGenres: GetGenres by inject()

    @ExperimentalCoroutinesApi
    val genresLiveData = getGenres().map {
        GenreBindingMapper.fromDomain(it)
    }.asLiveData()
}

