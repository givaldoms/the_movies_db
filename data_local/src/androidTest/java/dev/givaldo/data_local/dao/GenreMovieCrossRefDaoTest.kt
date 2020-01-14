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

        genreMovieCressRefDao.getAllGenreWithMovies().test {
            Assertions.assertEquals(it, genreMovies)
        }
    }

    @Test
    fun givenAMovieListAndAGenreListGetMoviesWithGenreReturnsMovieCrossRefList() = runBlocking {
        val movies = MovieEntityFactory.makeDumbList()
        val movies1 = MovieEntityFactory.makeDumbList()

        val genre = GenreEntityFactory.dumbInstance()
        val genre1 = GenreEntityFactory.dumbInstance()

        val genreMovieCrossRefList = GenreMovieCrossRefFactory.makeDumbList(genre, movies)
            .toTypedArray()

        val genreMovieCrossRefList1 = GenreMovieCrossRefFactory.makeDumbList(genre1, movies1)
            .toTypedArray()

        val genreWithMovies = GenreWithMovies(genre, movies)
        val genreWithMovies1 = GenreWithMovies(genre1, movies1)

        movieDao.insertAll(movies + movies1)
        genreDao.insertAll(listOf(genre, genre1))

        val ids = genreMovieCressRefDao.insertGenreWithMovies(*genreMovieCrossRefList)
        Assertions.assertTrue(ids.none { it == -1L })

        val ids1 = genreMovieCressRefDao.insertGenreWithMovies(*genreMovieCrossRefList1)
        Assertions.assertTrue(ids1.none { it == -1L })

        genreMovieCressRefDao.run {
            getMoviesWithGenreByGenreId(genre.genreId).test {
                Assertions.assertEquals(genreWithMovies, it)
            }

            getMoviesWithGenreByGenreId(genre1.genreId).test {
                Assertions.assertEquals(genreWithMovies1, it)
            }

            getMoviesWithGenreByGenreId(genre1.genreId).test {
                Assertions.assertNotEquals(genreWithMovies, it)
            }
        }
    }

    @Test
    fun givenAEmptyGenreWithMoviesListReturnsAEmptyList() = runBlocking {
        val genreWithMovies = listOf<GenreWithMovies>()

        genreMovieCressRefDao.getAllGenreWithMovies().test {
            Assertions.assertIterableEquals(genreWithMovies, it)
        }

    }

    @Test
    fun givenAMovieListAndAGenreReturnsMovieCrossRefList() = runBlocking {
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

        genreMovieCressRefDao.getAllGenreWithMovies().test {
            Assertions.assertIterableEquals(genreWithMovies, it)
        }

    }

}