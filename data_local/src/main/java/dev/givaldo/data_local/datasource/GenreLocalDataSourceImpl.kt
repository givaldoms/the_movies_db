package dev.givaldo.data_local.datasource

import android.util.Log
import dev.givaldo.data.datasource.local.GenreLocalDataSource
import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.mapper.GenreMapper
import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class GenreLocalDataSourceImpl(
    private val genreDao: GenreDao
) : GenreLocalDataSource {

    @ExperimentalCoroutinesApi
    override fun getGenres(): Flow<List<Genre>> {
        Log.d("OFFLINE_FIRST", "LOCAL -> getGenres")

        return genreDao.getAll()
            .map {
                Log.d("OFFLINE_FIRST", "LOCAL genres: $it")
                GenreMapper.toDomain(it)
            }.distinctUntilChanged()
    }

    @ExperimentalCoroutinesApi
    override suspend fun saveGenres(list: List<Genre>): Flow<List<Genre>> {
        val ids = genreDao.insertAll(GenreMapper.fromDomain(list))

        val hasErrors = ids.any { it == -1L }

        if (hasErrors) {
            throw error("Erro ao inserir dado")
        } else {
            return flowOf(list).take(1)
        }
    }
}