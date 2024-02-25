package com.example.tvshowapp.domain.use_case.fav_tv_show_use_case

import android.util.Log
import com.example.tvshowapp.common.Resource
import com.example.tvshowapp.data.localdb.TvShowFav
import com.example.tvshowapp.data.remote.dto.tv_show_dto.toTvShowList
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.repository.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SaveFavTvShowUseCase @Inject constructor(
    private val repository: TvShowRepository
) {
     suspend fun invoke(tvShow: TvShowFav)  {
        try {
            val tvShows = repository.saveFavShow(tvShow)
            Log.d("favv","success")
        }catch (e: Exception){
            Log.d("favv","failure")
        }
    }
}