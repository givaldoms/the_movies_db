package dev.givaldo.data_remote.datasource

import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.data_remote.mapper.GenreMapper
import dev.givaldo.data_remote.service.GenreWebService
import dev.givaldo.data_remote.utils.flowApi
import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class GenreRemoteDataSourceImpl(
    private val genreWebService: GenreWebService
) : GenreRemoteDataSource {

    @ExperimentalCoroutinesApi
    override fun getGenres(): Flow<List<Genre>> = flowApi {
        println("OFFLINE_FIRST REMOTE -> getGenres")

        delay(5000)

        genreWebService.getGenreList().genres
            .map {
                println("OFFLINE_FIRST REMOTE genres: $it")
                GenreMapper.toDomain(it)
            }
    }
}