package dev.givaldo.data_local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.factory.GenreEntityFactory
import dev.givaldo.data_local.factory.PrimitiveDataFactory
import dev.givaldo.data_local.model.entity.GenreEntity
import dev.givaldo.data_local.utils.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var genreDao: GenreDao

    @BeforeAll
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        genreDao = database.genreDao()
    }

    @AfterAll
    fun closeDatabase() {
        database.close()
    }

    @BeforeEach
    fun resetDatabase() {
        database.clearAllTables()
    }

    @Test
    fun givenAEmptyListReturnsEmpty() = runBlocking {

        val genres = GenreEntityFactory.makeDumbList(0)

        genreDao.getAll().test {
            Assertions.assertIterableEquals(it, genres)
        }
    }

    @Test
    fun givenAGenreListInsertAllValues() = runBlocking {
        val genres = GenreEntityFactory.makeDumbList().sortedBy { it.genreId }

        genreDao.insertAll(genres)

        genreDao.getAll().test {
            Assertions.assertIterableEquals(it, genres)
        }
    }

    @Test
    fun givenAGenreListAndDeleteHerReturnsEmpty() = runBlocking {
        val genres = GenreEntityFactory.makeDumbList().sortedBy { it.genreId }

        genreDao.insertAll(genres)
        genreDao.deleteAll(genres)

        genreDao.getAll().test {
            Assertions.assertEquals(it, listOf<GenreEntity>())
        }
    }

    @Test
    fun givenAExitingGenreListDatabaseValuesAreReplaced() = runBlocking {
        val genres = GenreEntityFactory.makeDumbList().sortedBy { it.genreId }
        val newGenres = genres.map {
            it.copy(
                it.genreId,
                PrimitiveDataFactory.makeDumbString()
            )
        }

        val ids = genreDao.insertAll(genres)
        Assertions.assertTrue(ids.none { it == -1L })

        val newids = genreDao.insertAll(newGenres)
        Assertions.assertTrue(newids.none { it == -1L })

        genreDao.getAll().test {
            Assertions.assertIterableEquals(it, newGenres)
        }
    }

}