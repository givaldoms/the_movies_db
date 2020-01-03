package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.data_remote.mapper.GenreMapper
import dev.givaldo.data_remote.service.GenreWebService
import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreRemoteDataSourceImpl(
    private val genreWebService: GenreWebService
) : GenreRemoteDataSource {

    override fun getGenres(): Flow<List<Genre>> = flow {
        emit(GenreMapper.toDomain(genreWebService.getGenreList().genres))
    }
}