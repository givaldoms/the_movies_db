package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.domain.model.Genre
import dev.givaldo.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class GenreRepositoryImpl(
    private val remote: GenreRemoteDataSource,
    private val local: MovieLocalDataSource
) : GenreRepository {

    override fun getGenres(): Flow<List<Genre>> = flow {
        val remote = remote.getGenres().single()
        local.saveGenres(remote).single()

        local.getGenres().collect {
            emit(it)
        }

    }

}