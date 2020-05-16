package dev.givaldo.data_remote.model

import com.google.gson.annotations.SerializedName

data class MovieApi(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val description: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("poster_path")
    val posterPath: String?

)