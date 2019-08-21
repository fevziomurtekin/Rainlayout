package com.fevziomurtekin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.lib_layout.view.*
import kotlinx.coroutines.*

class RainlayoutView : ConstraintLayout {

    /** attributes of layout **/
    private var dropSrc = resources.getDrawable(R.drawable.ic_umbrella)
    private var dropPerSecond = 10
    private var dropTintColor = resources.getColor(android.R.color.white)
    private var fallToDropTime = 100
    private var isColorful = false

    private var animationJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by RainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private var uiScope = CoroutineScope(Dispatchers.Main + animationJob)

    constructor(context: Context?) : super(context) { init(context,null,0,0) }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { init(context,attrs,0,0) }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init(context,attrs,defStyleAttr,0) }

    private fun init(context: Context?,attrs:AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
        context?.theme?.obtainStyledAttributes(attrs,R.styleable.rain,defStyleAttr,defStyleRes)?.let {
            dropSrc = it.getDrawable(R.styleable.rain_dropSrc)
            dropPerSecond = it.getInt(R.styleable.rain_dropPerSecond,10)
            dropTintColor = it.getColor(R.styleable.rain_dropTintColor,resources.getColor(android.R.color.white))
            fallToDropTime = it.getInt(R.styleable.rain_durationOfDropTime,100)
            isColorful = it.getBoolean(R.styleable.rain_isColorful,false)
            it.recycle()
            initViews()
        }
    }

    /** initViews for Rainlayout */
    private fun initViews(){
        val view = LayoutInflater.from(context!!).inflate(R.layout.lib_layout,null)
        this@RainlayoutView.apply {
            val rlAnimation = view.findViewById<RelativeLayout>(R.id.rl_animation)
            val constraintSet = ConstraintSet()
            constraintSet.clone(this)
            addView(rlAnimation)
            constraintSet.apply {
                connect(rlAnimation.id,ConstraintSet.RIGHT,this@RainlayoutView.id,ConstraintSet.RIGHT,0)
                connect(rlAnimation.id,ConstraintSet.LEFT,this@RainlayoutView.id,ConstraintSet.LEFT,0)
                connect(rlAnimation.id,ConstraintSet.TOP,this@RainlayoutView.id,ConstraintSet.TOP,0)
                connect(rlAnimation.id,ConstraintSet.BOTTOM,this@RainlayoutView.id,ConstraintSet.BOTTOM,0)
            }
            constraintSet.applyTo(this)
        }
        this@RainlayoutView.requestLayout()
        showAnimation()
    }


    fun showAnimation(){
        if(!uiScope.isActive){
            animationJob = SupervisorJob()
            uiScope = CoroutineScope(Dispatchers.Main + animationJob)
        }

        uiScope.launch {
            repeat(100000){
                RainAnimation.showAnimation(
                    context,
                    this@RainlayoutView.findViewById(R.id.rl_animation) as RelativeLayout,
                    fallToDropTime,
                    dropSrc,
                    dropTintColor,
                    isColorful)
                delay((1000/dropPerSecond).toLong())
            }
        }
    }

    fun animationClear() = animationJob.cancel()

}