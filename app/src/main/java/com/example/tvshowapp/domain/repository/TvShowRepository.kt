package com.example.tvshowapp.domain.repository


import com.example.tvshowapp.data.remote.dto.TvShowDto
import com.example.tvshowapp.domain.model.TvShow

interface TvShowRepository {

    suspend fun getTvShows(): TvShowDto

    suspend fun getTvShowsSearch(searchQuery:String): TvShowDto

}