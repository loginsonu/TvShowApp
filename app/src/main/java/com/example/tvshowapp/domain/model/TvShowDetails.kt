package com.example.tvshowapp.domain.model

import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.Season

data class TvShowDetails(
    val id: Int,
    val name: String,
    val posterPath: String,
    val seasons: List<SeasonDetails>,
    val overview:String

)