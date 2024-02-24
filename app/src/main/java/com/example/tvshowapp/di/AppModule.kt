package com.example.tvshowapp.di

import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.data.remote.TvShowApi
import com.example.tvshowapp.data.repository.TvShowRepositoryImpl
import com.example.tvshowapp.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTvShowApi(): TvShowApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvShowApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(api: TvShowApi): TvShowRepository {
        return TvShowRepositoryImpl(api)
    }

}


private class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.AUTH_TOKEN}")
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}