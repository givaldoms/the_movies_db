package dev.givaldo.data_local.datasource

import dev.givaldo.data.datasource.local.GenreLocalDataSource
import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.mapper.GenreMapper
import dev.givaldo.domain.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GenreLocalDataSourceImpl(
    private val genreDao: GenreDao
) : GenreLocalDataSource {

    override fun getGenres(): Flow<List<Genre>> {
        return genreDao.getAll().map {
            GenreMapper.toDomain(it)
        }
    }

    override suspend fun saveGenres(list: List<Genre>): Flow<List<Genre>> {
        val ids = genreDao.insertAll(GenreMapper.fromDomain(list))

        val hasErrors = ids.any { it == -1L }

        if (hasErrors) {
            throw error("Erro ao inserir dado")
        } else {
            return flowOf(list)
        }
    }
}