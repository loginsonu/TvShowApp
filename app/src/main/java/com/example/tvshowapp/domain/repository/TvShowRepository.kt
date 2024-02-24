package com.example.tvshowapp.domain.repository


import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.DetailsTvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.TvShowDto

interface TvShowRepository {

    suspend fun getTvShows(): TvShowDto

    suspend fun getTvShowsSearch(searchQuery:String): TvShowDto

    suspend fun getTvShowDetails(id:Int):DetailsTvShowDto

}