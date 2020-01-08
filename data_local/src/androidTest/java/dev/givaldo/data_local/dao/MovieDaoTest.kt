package dev.givaldo.data_local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.factory.MovieEntityFactory
import dev.givaldo.data_local.factory.PrimitiveDataFactory
import dev.givaldo.data_local.model.entity.MovieEntity
import dev.givaldo.data_local.utils.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao

    @BeforeAll
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = database.movieDao()
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
        val movies = MovieEntityFactory.makeDumbList(0)

        movieDao.getAll().test {
            Assertions.assertIterableEquals(it, movies)
        }
    }

    @Test
    fun givenAMovieListInsertAllValues() = runBlocking {
        val movies = MovieEntityFactory.makeDumbList().sortedBy { it.movieId }

        val ids = movieDao.insertAll(movies)
        Assertions.assertTrue(ids.none { it == -1L })

        movieDao.getAll().test {
            Assertions.assertIterableEquals(it, movies)
        }
    }

    @Test
    fun givenAMovieListAndDeleteHerReturnsEmpty() = runBlocking {
        val movies = MovieEntityFactory.makeDumbList().sortedBy { it.movieId }

        val ids = movieDao.insertAll(movies)
        Assertions.assertTrue(ids.none { it == -1L })

        movieDao.deleteAll(movies)

        movieDao.getAll().test {
            Assertions.assertIterableEquals(it, listOf<MovieEntity>())
        }
    }

    @Test
    fun givenAExitingMovieListDatabaseValuesAreReplaced() = runBlocking {
        val movies = MovieEntityFactory.makeDumbList().sortedBy { it.movieId }
        val newMovies = movies.map {
            it.copy(
                it.movieId,
                PrimitiveDataFactory.makeDumbString(),
                PrimitiveDataFactory.makeDumbString(),
                PrimitiveDataFactory.makeDumbString()
            )
        }

        val ids = movieDao.insertAll(movies)
        Assertions.assertTrue(ids.none { it == -1L })

        val newids = movieDao.insertAll(newMovies)
        Assertions.assertTrue(newids.none { it == -1L })

        movieDao.getAll().test {
            Assertions.assertIterableEquals(it, newMovies)
        }
    }

}