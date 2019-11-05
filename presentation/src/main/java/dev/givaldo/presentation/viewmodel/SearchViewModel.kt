package dev.givaldo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.givaldo.domain.interactor.movie.GetMovies
import dev.givaldo.presentation.mapper.MoviePresentationMapper
import dev.givaldo.presentation.model.MovieBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@FlowPreview
class SearchViewModel : ViewModel(), KoinComponent {

    private val getMovies: GetMovies by inject()
    private var currentPage = 1
    private var currentQuery: String = ""

    private var movieList = mutableListOf<MovieBinding>()

    private val _moviesLiveData = MutableLiveData<Result<List<MovieBinding>>>()
    val moviesLiveData: LiveData<Result<List<MovieBinding>>> = _moviesLiveData

    fun fetchMovies(query: String) {

        if (query.isBlank()) return
        currentQuery = query

        viewModelScope.launch {
            getMovies(
                params = GetMovies.Params(
                    query = query,
                    genre = null
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

    fun fetchMoreMovie() {
        fetchMovies(currentQuery)
    }
}