package dev.givaldo.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieBinding(
    val id: Long,
    val title: String,
    val description: String,
    val posterUrl: String
) : Parcelable