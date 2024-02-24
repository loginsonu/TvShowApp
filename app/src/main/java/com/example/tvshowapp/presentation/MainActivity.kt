package com.example.tvshowapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvshowapp.presentation.tv_show_list.TvShowListScreen
import com.example.tvshowapp.presentation.tv_show_list_details.TvShowDetailScreen
import com.example.tvshowapp.presentation.ui.theme.TVShowAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVShowAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TvShowApp()
                }
            }
        }
    }
}


@Composable
fun TvShowApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.TvShowListScreen.route
    ) {
        composable(
            route = Screen.TvShowListScreen.route
        ) {
            TvShowListScreen(navController)
        }
        composable(
            route = Screen.TvShowDetailScreen.route + "/{id}"
        ) {
            TvShowDetailScreen(navController)
        }
    }
}
