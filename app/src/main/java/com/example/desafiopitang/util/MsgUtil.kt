package com.example.desafiopitang.util

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

object MsgUtil {

    fun showMsg(msg:String, viewRoot: View?){
        if (viewRoot != null) {
            try {
                val snackBar = Snackbar.make(
                    viewRoot,
                    msg,
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
            }
            catch (e:Exception){
                Log.e("SnackBar", "Fail")}
        }
    }
}