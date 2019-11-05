package dev.givaldo.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreBinding(
    val id: Long,
    val title: String
) : Parcelable