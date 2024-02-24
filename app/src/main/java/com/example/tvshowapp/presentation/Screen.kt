package com.example.tvshowapp.presentation

sealed class Screen(val route: String) {
    object TvShowListScreen: Screen("tv_show_list_screen")
    object TvShowDetailScreen: Screen("tv_show_detail_screen")
}
