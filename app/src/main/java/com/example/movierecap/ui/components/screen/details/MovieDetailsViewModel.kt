package com.example.movierecap.ui.components.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierecap.data.model.Movie
import com.example.movierecap.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    private val repository: MovieRepository = MovieRepository()

    val movieFlow: MutableStateFlow<Movie?> = MutableStateFlow(
        value = null
    )

    fun getMovie(
        id: Int,
    ) {
        viewModelScope.launch(
            context = Dispatchers.IO,
        ) {
            movieFlow.update {
                repository.getMovieById(
                    id = id,
                )
            }
        }
    }
}