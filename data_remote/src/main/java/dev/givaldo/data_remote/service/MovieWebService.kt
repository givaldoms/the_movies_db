package dev.givaldo.data_remote.service

import dev.givaldo.data_remote.constants.MovieConstants.GENRES_QUERY
import dev.givaldo.data_remote.constants.MovieConstants.GET_GENRES_URL
import dev.givaldo.data_remote.constants.MovieConstants.GET_MOVIES_SEARCH_URL
import dev.givaldo.data_remote.constants.MovieConstants.GET_MOVIES_URL
import dev.givaldo.data_remote.constants.MovieConstants.PAGE_QUERY
import dev.givaldo.data_remote.model.MovieResponse
import dev.givaldo.domain.model.Genre
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieWebService {

    @GET(GET_MOVIES_URL)
    suspend fun getMovieList(
        @Query(GENRES_QUERY) genre: Long,
        @Query(PAGE_QUERY) page: Int
    ): List<MovieResponse>

    @GET(GET_MOVIES_SEARCH_URL)
    suspend fun getMovieSearchList(
        @Query(GENRES_QUERY) query: String,
        @Query(PAGE_QUERY) page: Int
    ): List<MovieResponse>


    @GET(GET_GENRES_URL)
    suspend fun getGenreList(): List<Genre>


}

