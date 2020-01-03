package dev.givaldo.data_remote.service

import dev.givaldo.data_remote.constants.MovieConstants.GENRES_PATH
import dev.givaldo.data_remote.constants.MovieConstants.GET_MOVIES_SEARCH_URL
import dev.givaldo.data_remote.constants.MovieConstants.GET_MOVIES_URL
import dev.givaldo.data_remote.constants.MovieConstants.PAGE_PATH
import dev.givaldo.data_remote.constants.MovieConstants.QUERY_PATH
import dev.givaldo.data_remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieWebService {

    @GET(GET_MOVIES_URL)
    suspend fun getMovieList(
        @Query(GENRES_PATH) genre: Long,
        @Query(PAGE_PATH) page: Int
    ): MovieResponse

    @GET(GET_MOVIES_SEARCH_URL)
    suspend fun getMovieSearchList(
        @Query(QUERY_PATH) query: String,
        @Query(PAGE_PATH) page: Int
    ): MovieResponse

}

