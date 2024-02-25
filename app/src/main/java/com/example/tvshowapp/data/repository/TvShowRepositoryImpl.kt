package com.example.tvshowapp.data.repository


import android.content.Context
import com.example.tvshowapp.data.localdb.AppDatabase
import com.example.tvshowapp.data.localdb.TvShowFav
import com.example.tvshowapp.data.remote.TvShowApi
import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.DetailsTvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.TvShowDto
import com.example.tvshowapp.data.remote.dto.tv_show_dto.toTvShowList
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.repository.TvShowRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TvShowRepositoryImpl @Inject constructor(
    private val api: TvShowApi,
    private val db: AppDatabase
) : TvShowRepository {

    override suspend fun getTvShows(): TvShowDto {
        val data = api.getTvShows()
        //here saving data to local database for local support in case of no internet
        db.getDao().deleteTvShowList() //deleting existing tvShow
        db.getDao().addTvShowList(data.toTvShowList()) //saving new
        return data
    }

    override suspend fun getTvShowsSearch(searchQuery: String): TvShowDto {
        return api.getTvShowsSearch(searchQuery)
    }

    override suspend fun getTvShowDetails(id: Int): DetailsTvShowDto {
        return  api.getTVShowDetails(id)
    }

    override suspend fun getSimilarTvShows(id: Int): TvShowDto {
        return api.getSimilarShows(id)
    }

    override suspend fun saveFavShow(tvShow: TvShowFav) {
        db.getDao().addFavTvShow(tvShow)
    }

    override suspend fun removeFavShow(id: Int) {
        db.getDao().removeFavShow(id)
    }

    override suspend fun getFavShowList(): List<TvShowFav> {
        return db.getDao().tvShowList()
    }

    override suspend fun getTvShowFromLocaldb(): List<TvShow> {
        return db.getDao().getListTvShow()
    }


}