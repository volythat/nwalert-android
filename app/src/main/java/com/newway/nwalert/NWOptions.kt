package com.newway.nwalert

import android.graphics.Color
import android.graphics.Typeface

class NWOptions(
    var marginContainer : Float = 30f, //dp
    var maxWidthContainer : Float = 350f, //dp
    var cornerContainer : Float = 12f, //dp
    var backgroundColor : Int = Color.WHITE,
    var titleColor : Int = Color.BLACK,
    var fontTitle : Typeface = Typeface.DEFAULT_BOLD,
    var titleTextSize : Float = 18f, //sp
    var messageColor : Int = Color.GRAY,
    var fontMessage : Typeface = Typeface.DEFAULT,
    var messageTextSize : Float = 14f,//sp
    var textButtonColor : Int = Color.WHITE,
    var fontButton : Typeface = Typeface.DEFAULT_BOLD,
    var buttonTextSize : Float = 15f,
    var buttonBackground : Int = Color.TRANSPARENT,
    var isCancelable : Boolean = true,
    var colorButtonHighlight : Int = Color.BLACK,
    var colorTextButtonHighlight : Int = Color.WHITE,
    var cornerButton : Float = 27f,
    var heightButton : Float = 54f, //dp
    var buttonAllCaps : Boolean = false
) {
}