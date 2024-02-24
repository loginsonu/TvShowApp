package com.example.tvshowapp.data.repository


import com.example.tvshowapp.data.remote.TvShowApi
import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.DetailsTvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.TvShowDto
import com.example.tvshowapp.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val api: TvShowApi
) : TvShowRepository {

    override suspend fun getTvShows(): TvShowDto {
        return api.getTvShows()
    }

    override suspend fun getTvShowsSearch(searchQuery: String): TvShowDto {
        return api.getTvShowsSearch(searchQuery)
    }

    override suspend fun getTvShowDetails(id: Int): DetailsTvShowDto {
        return  api.getTVShowDetails(id)
    }


}