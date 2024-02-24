package com.example.tvshowapp.data.remote.dto

import com.example.tvshowapp.domain.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowDto(
    val page: Int,
    val results: List<TvShowInfo>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun TvShowDto.toTvShowList(): List<TvShow> {
    return results.map {
        TvShow(
            id = it.id,
            name = it.name,
            posterPath = it.posterPath
        )
    }
}