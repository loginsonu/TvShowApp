package com.example.tvshowapp.domain.use_case.search_tv_show_use_case

import android.util.Log
import com.example.tvshowapp.common.Resource
import com.example.tvshowapp.data.remote.dto.toTvShowList
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.repository.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvShowsSearchUseCase @Inject constructor(
    private val repository: TvShowRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<TvShow>>> = flow {
        try {
            emit(Resource.Loading<List<TvShow>>())
            val tvShows = repository.getTvShowsSearch(query).toTvShowList()
            emit(Resource.Success<List<TvShow>>(tvShows))
            if(tvShows.isNotEmpty())
                emit(Resource.Success<List<TvShow>>(tvShows))
            else
                emit(Resource.Error<List<TvShow>>("No Tv Shows Found with : $query"))

        } catch(e: HttpException) {
            emit(Resource.Error<List<TvShow>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<TvShow>>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception){
            emit(Resource.Error<List<TvShow>>("Something Went Wrong"))
        }
    }.flowOn(Dispatchers.IO)
}