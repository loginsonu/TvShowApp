package com.example.tvshowapp.domain.use_case.fav_tv_show_use_case

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

class GetFavTvShowsUseCase @Inject constructor(
    private val repository: TvShowRepository
) {
    operator fun invoke(): Flow<Resource<List<TvShowFav>>> = flow {
        try {
            val tvShows = repository.getFavShowList()

            emit(Resource.Success<List<TvShowFav>>(tvShows))

        }catch (e: Exception){
            emit(Resource.Error<List<TvShowFav>>("Something Went Wrong"))
        }
    }.flowOn(Dispatchers.IO)
}