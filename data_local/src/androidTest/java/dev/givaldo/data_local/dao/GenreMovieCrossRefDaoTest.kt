package dev.givaldo.data_local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.factory.GenreEntityFactory
import dev.givaldo.data_local.factory.GenreMovieCrossRefFactory
import dev.givaldo.data_local.factory.MovieEntityFactory
import dev.givaldo.data_local.model.GenreWithMovies
import dev.givaldo.data_local.utils.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreMovieCrossRefDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var genreDao: GenreDao
    private lateinit var genreMovieCressRefDao: GenreMovieCrossRefDao

    @BeforeAll
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = database.movieDao()
        genreDao = database.genreDao()
        genreMovieCressRefDao = database.genreMovieCressRefDao()
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
        val genreMovies = GenreMovieCrossRefFactory.makeDumbList(0)

        genreMovieCressRefDao.getGenreWithMovies().test {
            Assertions.assertEquals(it, genreMovies)
        }
    }

    @Test
    fun givenAMovieListAndAGenreReturnsAgenreMovieCrossRefList() = runBlocking {
        val movies = MovieEntityFactory.makeDumbList()
        val genre = GenreEntityFactory.dumbInstance()
        val genreMovieCrossRefList = GenreMovieCrossRefFactory.makeDumbList(genre, movies)
        val genreWithMovies = listOf(GenreWithMovies(genre, movies))

        movieDao.insertAll(movies)
        genreDao.insert(genre)

        val ids = genreMovieCressRefDao.insertGenreWithMovies(
            *genreMovieCrossRefList.toTypedArray()
        )

        Assertions.assertTrue(ids.none { it == -1L })

        genreMovieCressRefDao.getGenreWithMovies().test {
            Assertions.assertIterableEquals(genreWithMovies, it)
        }

    }

}