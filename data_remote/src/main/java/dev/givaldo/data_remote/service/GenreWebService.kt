package dev.givaldo.data_remote.service

import dev.givaldo.data_remote.constants.MovieConstants
import dev.givaldo.data_remote.model.GenresResponse
import retrofit2.http.GET

interface GenreWebService {

    @GET(MovieConstants.GET_GENRES_URL)
    suspend fun getGenreList(): GenresResponse
}