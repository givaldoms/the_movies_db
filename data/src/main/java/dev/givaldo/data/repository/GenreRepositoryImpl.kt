package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.GenreLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class GenreRepositoryImpl(
    private val remote: GenreRemoteDataSource,
    private val local: GenreLocalDataSource
) : GenreRepository {

    @ExperimentalCoroutinesApi
    override fun getGenres(): Flow<List<Genre>> =
        flow {
            emitAll(local.getGenres().distinctUntilChanged())
            remote.getGenres().take(1)
                .catch {
                    throw it
                }.collect {
                    local.saveGenres(it)
                    emit(it)
                }
        }

}

