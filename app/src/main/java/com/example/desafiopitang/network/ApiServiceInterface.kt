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

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}