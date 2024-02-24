package com.example.tvshowapp.data.remote


import com.example.tvshowapp.data.remote.dto.TvShowDto
import com.example.tvshowapp.domain.model.TvShow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET("/3/trending/tv/day?language=en-US")
    suspend fun getTvShows(): TvShowDto

    @GET("/3/search/tv")
    suspend fun getTvShowsSearch(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TvShowDto

}