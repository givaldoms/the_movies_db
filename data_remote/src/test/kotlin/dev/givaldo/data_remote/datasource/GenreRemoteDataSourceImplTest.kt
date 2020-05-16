package dev.givaldo.data_remote.datasource

import dev.givaldo.data_remote.model.GenresResponse
import dev.givaldo.data_remote.service.GenreWebService
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class GenreRemoteDataSourceImplTest {

    private val genreWebServiceMock: GenreWebService = mockk()

    private val genreRemoteDataSourceImpl = GenreRemoteDataSourceImpl(genreWebServiceMock)

    @ExperimentalCoroutinesApi
    @Test
    fun webserviceIsCalled() = runBlocking {
        stubGetGenreList(GenresResponse(emptyList()))
        genreRemoteDataSourceImpl.getGenres().collect {}

        coVerify {
            genreWebServiceMock.getGenreList()
        }

    }

    private fun stubGetGenreList(genre: GenresResponse) {
        every { runBlocking { genreWebServiceMock.getGenreList() } } returns genre
    }


}