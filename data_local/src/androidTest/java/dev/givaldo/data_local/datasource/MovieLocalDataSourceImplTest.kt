package dev.givaldo.data_local.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.dao.GenreDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieLocalDataSourceImplTest {

    private lateinit var genresDao: GenreDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        genresDao = db.genreDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}