package com.example.desafiopitang.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImgUtil {
    fun loadImg(ctx:Context,url : String, iv:ImageView){
        Glide.with(ctx).load(url).fitCenter().into(iv)
    }
}