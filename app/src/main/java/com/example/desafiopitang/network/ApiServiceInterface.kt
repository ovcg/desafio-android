package com.example.desafiopitang.network

import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.data.models.MovieDetails
import com.example.desafiopitang.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("list?")
    fun getMovieList(@Query("page") page:String,
                     @Query("size") size:String): Observable<ArrayList<Movie>>

    @GET("detail/{id}")
    fun getMovie(@Path("id") id : String): Observable<MovieDetails>

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