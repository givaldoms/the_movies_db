package dev.givaldo.domain.interactor.genres

import dev.givaldo.domain.core.UseCase
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetGenres(
    private val genreRepository: GenreRepository
) : UseCase<List<Genre>, Unit>() {

    override fun run(params: Unit?): Flow<List<Genre>> {
        return genreRepository.getGenres()
//            .map {
//                listOf(it.first())
//            }
    }
}