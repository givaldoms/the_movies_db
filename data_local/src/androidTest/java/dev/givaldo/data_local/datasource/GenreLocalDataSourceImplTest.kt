package dev.givaldo.data_local.datasource

import dev.givaldo.data_local.dao.GenreDao
import dev.givaldo.data_local.factory.GenreEntityFactory
import dev.givaldo.data_local.mapper.GenreMapper
import dev.givaldo.data_local.utils.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GenreLocalDataSourceImplTest {

    private val dumbGenreList = GenreEntityFactory.makeDumbList()
    private var dumbIdsList = dumbGenreList.map { it.genreId }

    private var genreDao: GenreDao = mockk {
        every { getAll() } returns flowOf(dumbGenreList)
    }

    private val localDataSource = GenreLocalDataSourceImpl(genreDao)

    @Test
    fun saveGenresReturnsItself() = runBlocking {
        stubGenreInsertAllReturns(dumbIdsList)

        val domainGenreList = GenreMapper.toDomain(dumbGenreList)

        localDataSource.saveGenres(domainGenreList)
            .test {
                Assertions.assertIterableEquals(it, domainGenreList)
            }
    }

    @Test
    fun saveGenresReturnsError() {
        stubGenreInsertAllReturns(dumbIdsList.map { -1L })
        val domainGenreList = GenreMapper.toDomain(dumbGenreList)

        Assertions.assertThrows(IllegalStateException::class.java) {
            runBlocking {
                localDataSource.saveGenres(domainGenreList)
                    .test {}
            }
        }
    }


    private fun stubGenreInsertAllReturns(items: List<Long>) {
        every { runBlocking { genreDao.insertAll(any()) } } returns items

    }

}