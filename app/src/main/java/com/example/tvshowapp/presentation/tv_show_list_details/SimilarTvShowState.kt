package com.example.tvshowapp.presentation.tv_show_list_details

import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.model.TvShowDetails

data class SimilarTvShowState(
    val isLoading: Boolean = false,
    val error: String = "",
    val similarTvShows: List<TvShow> = emptyList()
)