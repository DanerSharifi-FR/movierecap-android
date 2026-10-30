package com.example.movierecap.ui.components.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.movierecap.R

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: MovieDetailsViewModel = viewModel()

    val movie by viewModel.movieFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Ici, vous pouvez effectuer des actions lorsque l'écran est affiché
        // Par exemple, vous pouvez charger les détails du film à partir d'une source de données
        // ou effectuer d'autres opérations nécessaires.
        viewModel.getMovie(id = movieId)
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "MovieDetail",
            style = MaterialTheme.typography.headlineMedium,
        )
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9xNgRNPydFWG_SM-i-8ffTj0hANvlnE78SdIbFFA8aS1GrUdRuDn3eCe6&s=10", // movie.posterUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        8.dp
                    )
                )
                .height(100.dp)
        )
        // Rappel : movie?.let {...}
        // Si movie n'est pas null, alors on execute le bloc de code à l'intérieur de let
        movie?.let { movie ->
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(
                    id = R.string.movie_author,
                    movie.author,
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(
                    id = R.string.movie_rate,
                    movie.rate,
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Button(
            onClick = {
                // Action when the button is clicked
                onBack()
            },
            // onClick = onBack,
        ) {
            Text(text = "Retour")
        }


    }
}