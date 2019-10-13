package com.example.desafiopitang.util

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.desafiopitang.R
import com.google.android.material.snackbar.Snackbar

object MsgUtil {

    fun showMsg(ctx: Context, msg:String, viewRoot: View?, error:Boolean){
        if (viewRoot != null) {
            try {
                val snackBar = Snackbar.make(
                    viewRoot,
                    msg,
                    Snackbar.LENGTH_LONG
                )
      /*          if (error) {
                    snackBar.setBackgroundTint(ContextCompat.getColor(ctx, R.color.colorAlert))
                } else {
                    snackBar.setBackgroundTint(ContextCompat.getColor(ctx, R.color.colorSuccess))
                }
                snackBar.setTextColor(ContextCompat.getColor(ctx, R.color.white))*/
                snackBar.show()
            }
            catch (e:Exception){
                Log.e("SnackBar", "Fail")}
        }
    }
}