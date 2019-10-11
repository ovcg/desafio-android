package com.example.desafiopitang.network

import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("movies")
    fun getMovieList(): Observable<List<Movie>>

    @GET("detail")
    fun getMovie(): Observable<Movie>

    companion object Factory {
        fun create(url:String): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}