package dev.givaldo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.givaldo.domain.interactor.movie.GetMovies
import dev.givaldo.presentation.mapper.GenreBindingMapper
import dev.givaldo.presentation.mapper.MoviePresentationMapper
import dev.givaldo.presentation.model.GenreBinding
import dev.givaldo.presentation.model.MovieBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@FlowPreview
class MoviesViewModel(
    private val genre: GenreBinding
) : ViewModel(), KoinComponent {

    private val getMovies: GetMovies by inject()
    private var currentPage = 1

    private var movieList = mutableListOf<MovieBinding>()

    private val _moviesLiveData = MutableLiveData<Result<List<MovieBinding>>>()
    val moviesLiveData: LiveData<Result<List<MovieBinding>>> = _moviesLiveData

    init {
        fetchMovies()
    }

    fun fetchMovies() = viewModelScope.launch {
        getMovies(
            params = GetMovies.Params(
                genre = GenreBindingMapper.toDomain(genre),
                page = currentPage
            )
        ).map {
            currentPage++
            MoviePresentationMapper.fromDomain(it)
        }.collect {
            movieList.addAll(it)
            _moviesLiveData.value = Result.success(movieList)
        }
    }

}