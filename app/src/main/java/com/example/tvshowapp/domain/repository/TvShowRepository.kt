package com.example.tvshowapp.domain.repository


import com.example.tvshowapp.data.localdb.TvShowFav
import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.DetailsTvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.TvShowDto
import com.example.tvshowapp.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    suspend fun getTvShows(): TvShowDto

    suspend fun getTvShowsSearch(searchQuery:String): TvShowDto

    suspend fun getTvShowDetails(id:Int):DetailsTvShowDto

    suspend fun getSimilarTvShows(id:Int):TvShowDto

    suspend fun saveFavShow(tvShow: TvShowFav)

    suspend fun removeFavShow(id: Int)

    suspend fun getFavShowList(): List<TvShowFav>

    suspend fun getTvShowFromLocaldb(): List<TvShow>
}