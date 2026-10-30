package com.example.movierecap.data.model

import com.example.movierecap.data.remote.MovieDto

data class Movie(
    val id: Int,
    val title: String,
    val author: String,
    val rate: Float,
) {
    companion object {
        fun fromDto(dto: MovieDto): Movie {
            return Movie(
                id = dto.id.toInt(),
                title = dto.title,
                author = dto.director,
                rate = (1f - dto.rating) * 5f,
            )
        }
    }
}

