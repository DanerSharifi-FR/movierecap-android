package com.example.movierecap.ui.components.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movierecap.data.model.Movie
import com.example.movierecap.ui.components.screen.MovieCard

@Composable
fun MovieListScreen(
    onClickItem: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: MovieListViewModel = viewModel()

    // Collect the movies from the ViewModel's Flow as a State
    // Sans "WithLifecycle", si l'application est mise en arrière plan,
    // la collection de la Flow continue et peut provoquer des fuites de mémoire.
    // val movies = viewModel.moviesFlow.collectAsState()

    // Collect the movies from the ViewModel's Flow as a State
    val movies by viewModel.moviesFlow.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                title = movie.title,
                author = movie.author,
                rate = movie.rate,
                modifier = Modifier
                    .clickable {
                        onClickItem(movie)
                    }
            )
            Spacer(modifier = Modifier.height(4.dp)) // Espacement entre les cartes
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieListScreen() {
    val movies = listOf(
        Movie(1, "The Dark Knight", "Christopher Nolan", 4.8f),
        Movie(2, "Inception", "Christopher Nolan", 4.7f),
        Movie(3, "Interstellar", "Christopher Nolan", 4.6f)
    )
    MovieListScreen(onClickItem = {})
}