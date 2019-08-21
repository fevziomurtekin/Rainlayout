package com.fevziomurtekin.rainground

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlin.random.Random

object RainAnimation {


    /** rain animation function */
    fun showAnimation(
        context: Context?,
        view: RelativeLayout,
        resourceId: Int,
        childColor:Int,
        isColorful: Boolean
    ) {

        // start of view x position
        val startX = Random.nextInt(0,RaingroundExt.getWidth(context)).toFloat()
        // rotation of view
        var rotationView = Random.nextInt(0,360).toFloat()
        // scale of view
        var scaleView = Random.nextFloat()

        context?.run {
            val iv = ImageView(context)
            view.addView(iv)
            iv.apply {
                setImageResource(resourceId)
                x = startX
                scaleY = scaleView
                scaleY = scaleView
                rotation = rotationView
                if(!isColorful) setColorFilter(childColor)
                else setColorFilter(Color.argb(255,Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)))
            }
        }

    }

    fun clearAnimation(){


    }

}