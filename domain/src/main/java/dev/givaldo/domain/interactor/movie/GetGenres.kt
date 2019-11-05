package dev.givaldo.domain.interactor.movie

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
class GetGenres(
    private val movieRepository: MovieRepository
) : UseCase<List<Genre>, Unit>() {

    override fun run(params: Unit?): Flow<List<Genre>> {
        return movieRepository.getGenres()
    }
}

