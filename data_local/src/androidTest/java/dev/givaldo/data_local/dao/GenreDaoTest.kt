package dev.givaldo.data_local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.factory.GenreEntityFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

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

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun getAllWhenEmptyReturnsEmpty() = runBlocking {
        database.clearAllTables()

        val genres = GenreEntityFactory.makeDumbList(0)

        genreDao.getAll().take(1).collect {
            Assertions.assertIterableEquals(it, genres)
        }
    }

    @Test
    fun getAllReturnsCorrectValues() = runBlocking {
        val genres = GenreEntityFactory.makeDumbList().sortedBy { it.genreId }

        genreDao.insertAll(genres)

        genreDao.getAll().take(1).collect {
            Assertions.assertIterableEquals(it, genres)
        }
    }

}