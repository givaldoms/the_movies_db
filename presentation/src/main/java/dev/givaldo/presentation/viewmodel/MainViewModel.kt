package dev.givaldo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.givaldo.domain.interactor.movie.GetGenres
import dev.givaldo.domain.interactor.movie.GetMovies
import dev.givaldo.domain.model.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@FlowPreview
class MainViewModel : ViewModel() {

    private val getMovies = GetMovies(TODO())
    private val getGenres = GetGenres(TODO())

    private val moviesLiveData: LiveData<List<Movie>> = liveData {
        getMovies().asLiveData()
    }

    private val genresLiveData: LiveData<List<GetGenres>> = liveData {
        getGenres().asLiveData()
    }

}

@FlowPreview
private fun <T> Flow<T>.asLiveData(): LiveData<T> = liveData {
    collect {
        emit(it)
    }
}
