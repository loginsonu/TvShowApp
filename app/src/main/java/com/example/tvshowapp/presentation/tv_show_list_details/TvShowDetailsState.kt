package com.example.tvshowapp.presentation.tv_show_list_details

import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.model.TvShowDetails

data class TvShowDetailsState(
    val isLoading: Boolean = false,
    val tvShowsDetails: TvShowDetails? =null,
    val error: String = ""
)