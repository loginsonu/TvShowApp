package com.example.tvshowapp.domain.use_case.get_tv_shows

import com.example.tvshowapp.common.Resource
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

class GetTvShowsUseCase @Inject constructor(
    private val repository: TvShowRepository
) {
    operator fun invoke(): Flow<Resource<List<TvShow>>> = flow {
        try {
            emit(Resource.Loading<List<TvShow>>())
            val tvShows = repository.getTvShows().toTvShowList()
            if(tvShows.isNotEmpty())
              emit(Resource.Success<List<TvShow>>(tvShows))
            else
              emit(Resource.Error<List<TvShow>>("No Tv Shows Found"))
        } catch(e: HttpException) {
            emit(Resource.Error<List<TvShow>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<TvShow>>("Couldn't reach server. Check your internet connection."))
            //here we can get data from local database
            val getTvShowLocal = repository.getTvShowFromLocaldb()
            if(getTvShowLocal.isNotEmpty())
            emit(Resource.Success<List<TvShow>>(getTvShowLocal))

        }catch (e: Exception){
            emit(Resource.Error<List<TvShow>>("Something Went Wrong"))
        }
    }.flowOn(Dispatchers.IO)
}