package com.example.tvshowapp.data.repository


import com.example.tvshowapp.data.remote.TvShowApi
import com.example.tvshowapp.data.remote.dto.TvShowDto
import com.example.tvshowapp.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val api: TvShowApi
) : TvShowRepository {

    override suspend fun getTvShows(): TvShowDto {
        return api.getTvShows()
    }


}