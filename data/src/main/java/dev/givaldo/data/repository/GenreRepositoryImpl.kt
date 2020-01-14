package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.GenreLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class GenreRepositoryImpl(
    private val remote: GenreRemoteDataSource,
    private val local: GenreLocalDataSource
) : GenreRepository {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun getGenres(): Flow<List<Genre>> = flow {
        local.getGenres().distinctUntilChanged()
            .onEach {
                emit(it)
            }
            .flatMapConcat {
                remote.getGenres().flatMapConcat {
                    local.saveGenres(it)
                }
            }
            .collect()
    }
}

