package dev.givaldo.data_remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: List<MovieApi>

)