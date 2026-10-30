package com.example.movierecap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movierecap.ui.components.screen.details.MovieDetailScreen
import com.example.movierecap.ui.components.screen.list.MovieListScreen
import com.example.movierecap.ui.navigation.MovieNavGraph
import com.example.movierecap.ui.theme.MovieRecapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieRecapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = MovieNavGraph.MovieList,
                        ) {
                            composable<MovieNavGraph.MovieList> {
                                MovieListScreen(
                                    onClickItem = { movie ->
                                        navController.navigate(
                                            route = MovieNavGraph.MovieDetails(
                                                id = movie.id,
                                            ),
                                        )
                                    }
                                )
                            }
                            /*
                        composable(
                            route = "movie_details/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType }),
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            MovieDetail(
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                         */
                            composable<MovieNavGraph.MovieDetails> { backStackEntry ->
                                val movieNavData =
                                    backStackEntry.toRoute<MovieNavGraph.MovieDetails>()
                                MovieDetailScreen(
                                    movieId = movieNavData.id,
                                    onBack = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}