package com.fevziomurtekin.rainground

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout

class RaingroundView : ConstraintLayout {

    constructor(context: Context?) : super(context) { init(context,null,0,0) }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { init(context,attrs,0,0) }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init(context,attrs,defStyleAttr,0) }

    private fun init(context: Context?,attrs:AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
        context?.theme?.obtainStyledAttributes(attrs,R.styleable.view,defStyleAttr,defStyleRes).let {
            if(it==null){
                //TODO init all parameters.
                initViews()
            }
        }
    }

    /** initViews for Rainground */
    private fun initViews(){


    }

}