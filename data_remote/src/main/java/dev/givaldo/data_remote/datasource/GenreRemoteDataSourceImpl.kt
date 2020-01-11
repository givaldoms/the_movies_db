package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.data_remote.mapper.GenreMapper
import dev.givaldo.data_remote.service.GenreWebService
import dev.givaldo.data_remote.utils.flowApi
import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow

class GenreRemoteDataSourceImpl(
    private val genreWebService: GenreWebService
) : GenreRemoteDataSource {

    override fun getGenres(): Flow<List<Genre>> = flowApi {
        genreWebService.getGenreList().genres
            .map {
                GenreMapper.toDomain(it)
            }
    }

}