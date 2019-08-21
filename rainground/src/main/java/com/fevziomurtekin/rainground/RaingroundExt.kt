package com.fevziomurtekin.rainground

import android.app.Activity
import android.content.Context
import android.graphics.Point

object RaingroundExt {

    fun getWidth(context: Context?):Int = context?.resources?.displayMetrics?.widthPixels.let {
        it ?: 0
    }

    fun getHeight(context: Context?):Int = context?.resources?.displayMetrics?.heightPixels.let {
        it ?: 0
    }

}