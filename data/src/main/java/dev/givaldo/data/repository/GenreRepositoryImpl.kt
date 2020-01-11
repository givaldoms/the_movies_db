package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl(
    private val remote: GenreRemoteDataSource,
    private val local: MovieLocalDataSource
) : GenreRepository {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun getGenres(): Flow<List<Genre>> = flow {
        //TODO offline first behavior: listener local storage and update it with remote web service. obs: chanel must handle about all errors
        emitAll(remote.getGenres())
    }

}