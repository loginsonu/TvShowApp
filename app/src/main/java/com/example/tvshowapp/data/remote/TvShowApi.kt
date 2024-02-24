package com.example.tvshowapp.data.remote


import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.DetailsTvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.TvShowDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET("/3/trending/tv/week?language=en-US")
    suspend fun getTvShows(): TvShowDto

    @GET("/3/search/tv")
    suspend fun getTvShowsSearch(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TvShowDto

    @GET("/3/tv/{id}?language=en-US")
    suspend fun getTVShowDetails(@Path("id") id: Int): DetailsTvShowDto


    @GET("/3/tv/{id}/similar?language=en-US&page=1")
    suspend fun getSimilarShows(@Path("id") id: Int): TvShowDto
}