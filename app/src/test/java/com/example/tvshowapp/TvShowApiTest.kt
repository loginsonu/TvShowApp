package com.example.tvshowapp

import com.example.tvshowapp.data.remote.TvShowApi
import com.example.tvshowapp.util.DataUtil
import com.google.common.truth.Truth
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kotlinx.coroutines.test.runTest

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvShowApiTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var api : TvShowApi

    @Before
    fun setUp(){

        mockWebServer = MockWebServer()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url(path = "/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TvShowApi::class.java)
    }

    @Test
    fun `BreedsData expected NoData`() = runTest{

        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = api.getTvShows()
        mockWebServer.takeRequest()

        Truth.assertThat(response.results).isNull()
    }


    @Test
    fun `BreedsData expected Data`() = runTest{

        val mockResponse = MockResponse()
        val content = DataUtil.getRawDataFromFile("/dummy_data.json")
        mockResponse.setBody(content)
        mockResponse.setResponseCode(code = 200)
        mockWebServer.enqueue(mockResponse)

        val response = api.getTvShows()
        mockWebServer.takeRequest()

        Truth.assertThat(response.results.size).isGreaterThan(0)
    }

    @Test
    fun `BreedsData expected Error 500`() = runTest{
        try {
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(code = 500)
            mockWebServer.enqueue(mockResponse)

            api.getTvShows()
            mockWebServer.takeRequest()

        } catch (e: HttpException) {
            Truth.assertThat(e.code()).isEqualTo(500)
            Truth.assertThat(e.message()).isEqualTo("Server Error")
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}