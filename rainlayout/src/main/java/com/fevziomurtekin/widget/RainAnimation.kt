package com.fevziomurtekin.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlin.random.Random

object RainAnimation {

    /**
     * @param context, view base context
     * @param parentView, raingroundview
     * @param fallTime, number of drops per second
     * @param resourceId, will apply to rain animation on resourceId of view
     * @param childColor, color of rain drop
     * @param isColorful, Will be colorful?
     * */

    /** rain animation function */
    fun showAnimation(
        context: Context?,
        parentView: RelativeLayout,
        fallTime:Int,
        src: Drawable,
        childColor:Int,
        isColorful: Boolean
    ) {

        // start of view x position
        val startX = Random.nextInt(0,RainlayoutExt.getWidth(context)).toFloat()
        val stopY =  RainlayoutExt.getHeight(context).toFloat()
        // rotation of view
        val rotationView = Random.nextInt(0,360).toFloat()
        // scale of view
        val scaleView = Random.nextFloat()

        context?.runCatching {
            val iv = ImageView(context)
            parentView.addView(iv)
            iv.apply {
                setImageDrawable(src)
                x = startX
                scaleY = scaleView
                scaleY = scaleView
                rotation = rotationView
                imageTintList = ColorStateList.valueOf(
                    if(!isColorful) childColor
                    else Color.argb(255,Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                )
            }

            val objectAnimator = ObjectAnimator.ofFloat(
                iv,
                "translationY",
                0.toFloat(),
                stopY
            ).apply {
                interpolator = AccelerateInterpolator()
                duration = fallTime.toLong()

                addListener(object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        parentView.removeView(iv.parent as ViewGroup)
                    }

                    override fun onAnimationCancel(animation: Animator?) {}

                    override fun onAnimationStart(animation: Animator?) {}
                })
            }

            objectAnimator.start()

        }

    }

}