package com.example.desafiopitang.util

import android.content.SharedPreferences
import com.example.desafiopitang.data.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonUtil {

    private inline fun <reified T> toJson(src: T): String {
        return Gson().toJson(src)
    }

    private inline fun <reified T> fromJson(src: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(src, type)
    }

    fun serialize(key : String, sharedPref : SharedPreferences, movies : ArrayList<Movie>){
        val editor = sharedPref.edit()
        editor.putString(key, toJson(movies))
        editor.apply()
    }

    fun deserialize(key : String, sharedPref : SharedPreferences):ArrayList<Movie>{
        val movies = sharedPref.getString(key, "")
        if (movies.isNullOrBlank()){
            return ArrayList()
        }
        return fromJson(movies!!) as ArrayList<Movie>
    }

}