package dev.givaldo.data.datasource.local

import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreLocalDataSource {

    fun getGenres(): Flow<List<Genre>>

    suspend fun saveGenres(list: List<Genre>): Flow<List<Genre>>

}