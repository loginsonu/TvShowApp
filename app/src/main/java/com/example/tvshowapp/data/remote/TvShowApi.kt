package com.example.tvshowapp.data.remote


import com.example.tvshowapp.data.remote.dto.TvShowDto
import com.example.tvshowapp.domain.model.TvShow
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowApi {

    @GET("/3/trending/tv/day?language=en-US")
    suspend fun getTvShows(): TvShowDto

}