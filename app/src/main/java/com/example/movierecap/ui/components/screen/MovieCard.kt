package com.example.movierecap.ui.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    rate: Float,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$rate/5",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "De $author",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieCard() {
    MovieCard(
        title = "The Dark Knight",
        author = "Christopher Nolan",
        rate = 4.8f
    )
}
