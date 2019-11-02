package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOn

@FlowPreview
class GetGenres(
    private val movieRepository: MovieRepository
) : UseCase<List<Genre>, Unit>() {

    override suspend fun invoke(params: Unit?) = movieRepository.getGenres()
        .flowOn(Dispatchers.IO)

}

