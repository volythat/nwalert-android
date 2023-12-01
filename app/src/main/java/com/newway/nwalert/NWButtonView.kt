package com.newway.nwalert

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat

class NWButtonView : AppCompatButton {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setBackgroundColor(R.drawable.bg_button)

    }

    fun setUpView(button:NWButton,options:NWOptions){
        text = button.title
        textSize = options.buttonTextSize
        typeface = options.fontButton
        isAllCaps = options.buttonAllCaps

        val gradientDrawable = GradientDrawable()

        if (button.isHighLight){
            setTextColor(options.colorTextButtonHighlight)
            gradientDrawable.setColor(options.colorButtonHighlight)
        }else{
            setTextColor(options.textButtonColor)
            gradientDrawable.setColor(options.buttonBackground)
        }

        gradientDrawable.setStroke(2,options.colorButtonHighlight)
        gradientDrawable.cornerRadius = convertDpToPixel(options.cornerButton)

        background = gradientDrawable

        val params = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.height = convertDpToPixel(options.heightButton).toInt()
        params.setMargins(0, convertDpToPixel(6f).toInt(), 0, convertDpToPixel(6f).toInt())

        layoutParams = params
    }
    private fun convertDpToPixel(dp: Float): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}