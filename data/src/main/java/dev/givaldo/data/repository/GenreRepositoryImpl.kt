package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.GenreLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart

class GenreRepositoryImpl(
    private val remote: GenreRemoteDataSource,
    private val local: GenreLocalDataSource
) : GenreRepository {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun getGenres(): Flow<List<Genre>> = local.getGenres()
        .combine(remote.getGenres().withDefaultValue()) { l, r ->
            local.saveGenres(r)
            l
        }

}


@ExperimentalCoroutinesApi
fun <T> Flow<List<T>>.withDefaultValue() = onStart {
    emit(listOf())
}
