package com.fevziomurtekin.rainground

import android.content.Context
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class RainViewModel :ViewModel(){

    private var animationJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by RainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */

    private var uiScope = CoroutineScope(Dispatchers.Main + animationJob)


    /**
     * @param context, view base context
     * @param view, raingroundview
     * @param dropsPerSecond, number of drops per second
     * @param imageResource, will apply to rain animation on resourceId of view
     * @param childViewColor, color of rain drop
     * @param isColorful, Will be colorful?
     * */
    fun showAnimation(context: Context?,
                      view: RaingroundView,
                      dropsPerSecond:Int,
                      imageResource:Int,
                      childViewColor:Int,
                      isColorful:Boolean){
        if(!uiScope.isActive){
            animationJob = SupervisorJob()
            uiScope = CoroutineScope(Dispatchers.Main + animationJob)
        }

        uiScope.launch {
            repeat(100000){
                RainAnimation.showAnimation(
                    context,
                    view.findViewById(R.id.rl_animation) as RelativeLayout,
                    imageResource,
                    childViewColor,
                    isColorful)
                delay((1000/dropsPerSecond).toLong())
            }
        }
    }


    /** if change to page, stopped animation. */
    fun clearAnimation() = onCleared()


    override fun onCleared() {
        super.onCleared()
        animationJob.cancel()
    }
}