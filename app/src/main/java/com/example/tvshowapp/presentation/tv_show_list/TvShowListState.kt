package com.example.tvshowapp.presentation.tv_show_list

import com.example.tvshowapp.domain.model.TvShow


data class TvShowListState(
    val isLoading: Boolean = false,
    val tvShows: List<TvShow> = emptyList(),
    val error: String = "",
    val isSearch : Boolean =false,
    val stateSearchText : String =""
)

data class FavTvShowState(
    val favTvShows: List<TvShow> = emptyList()
)
