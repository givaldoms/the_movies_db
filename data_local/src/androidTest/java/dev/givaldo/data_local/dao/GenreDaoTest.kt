package dev.givaldo.data_local.dao

import dev.givaldo.data_local.core.AppDatabase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GenreDaoTest {

    @Nested
    inner class A {


        private lateinit var database: AppDatabase
        private lateinit var genreDao: GenreDao

//    @Before
//    fun createDatabase() {
//        val context: Context = ApplicationProvider.getApplicationContext()
//        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
//        genreDao = database.genreDao()
//    }
//
//    @After
//    fun closeDatabase() {
//        database.close()
//    }

        @Test
        fun adhjkdhkhjkhAJJK() {
            assertEquals("", "")
        }

    }
}