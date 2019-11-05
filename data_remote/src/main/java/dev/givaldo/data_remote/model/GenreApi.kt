package dev.givaldo.data_remote.model

import com.google.gson.annotations.SerializedName

data class GenreApi(

    @SerializedName("id") val id: Long,
    @SerializedName("name") val title: String

)