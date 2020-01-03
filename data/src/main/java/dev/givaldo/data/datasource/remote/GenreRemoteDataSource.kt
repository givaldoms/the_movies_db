package dev.givaldo.data.datasource.remote

import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {

    fun getGenres(): Flow<List<Genre>>

}