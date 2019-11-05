package dev.givaldo.data_remote.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(

    @SerializedName("genres") val genres: List<GenreApi>

)