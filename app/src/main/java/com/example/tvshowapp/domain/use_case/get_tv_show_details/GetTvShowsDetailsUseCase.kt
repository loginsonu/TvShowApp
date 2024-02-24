package com.example.tvshowapp.domain.use_case.get_tv_show_details

import android.util.Log
import com.example.tvshowapp.common.Resource
import com.example.tvshowapp.data.remote.dto.details_tv_shows_dto.toTvShowDetails
import com.example.tvshowapp.data.remote.dto.tv_show_dto.toTvShowList
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.domain.model.TvShowDetails
import com.example.tvshowapp.domain.repository.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvShowsDetailsUseCase @Inject constructor(
    private val repository: TvShowRepository
) {
    operator fun invoke(id:Int): Flow<Resource<TvShowDetails>> = flow {
        try {
            emit(Resource.Loading<TvShowDetails>())
            val tvShows = repository.getTvShowDetails(id).toTvShowDetails()

            emit(Resource.Success<TvShowDetails>(tvShows))

        } catch(e: HttpException) {
            emit(Resource.Error<TvShowDetails>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<TvShowDetails>("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception){
            //emit(Resource.Error<TvShowDetails>("Something Went Wrong"))
            emit(Resource.Error<TvShowDetails>(e.localizedMessage?:""))
        }
    }.flowOn(Dispatchers.IO)
}