package com.example.movierecap.ui.components.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierecap.data.model.Movie
import com.example.movierecap.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import android.util.Log

class MovieListViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()

    val moviesFlow: MutableStateFlow<List<Movie>> = MutableStateFlow(
        value = emptyList()
    )

    // Se lance à l'initialisation de la classe
    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(
            context = Dispatchers.IO,
        ) {
            try {
                moviesFlow.update { repository.getAllMovies() }
            } catch (exception: IOException) {
                Log.e(TAG, "Unable to load movies from the API; using local data.", exception)
                moviesFlow.update { repository.getLocalMovies() }
            } catch (exception: HttpException) {
                Log.e(TAG, "Movie API returned HTTP ${exception.code()}; using local data.", exception)
                moviesFlow.update { repository.getLocalMovies() }
            } catch (exception: SerializationException) {
                Log.e(TAG, "Unable to decode the movie API response; using local data.", exception)
                moviesFlow.update { repository.getLocalMovies() }
            }
        }
    }

    private companion object {
        const val TAG = "MovieListViewModel"
    }

}