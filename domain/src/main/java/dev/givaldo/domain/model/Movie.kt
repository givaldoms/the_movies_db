package dev.givaldo.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val description: String,
    val posterUrl: String
)