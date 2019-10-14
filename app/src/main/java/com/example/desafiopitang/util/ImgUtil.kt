package com.example.desafiopitang.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.desafiopitang.R

object ImgUtil {
    fun loadImg(ctx:Context,url : String, iv:ImageView){
        Glide.with(ctx).load(url).fitCenter().error(R.drawable.ic_image_black_24dp).into(iv)
    }

    fun loadImgBig(ctx:Context, url : String, iv:ImageView){
        Glide.with(ctx).load(url).error(R.drawable.ic_image_black_24dp).into(iv)
    }

}