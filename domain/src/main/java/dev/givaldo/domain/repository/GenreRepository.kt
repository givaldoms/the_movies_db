package dev.givaldo.domain.repository

import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    fun getGenres(): Flow<List<Genre>>

}