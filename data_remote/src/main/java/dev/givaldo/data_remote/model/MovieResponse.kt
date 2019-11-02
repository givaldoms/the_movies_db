package dev.givaldo.data_remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("original_title")
    val originalTitle: String? = "",

    @SerializedName("poster_path")
    val posterPath: String? = ""
)