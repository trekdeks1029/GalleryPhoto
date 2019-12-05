package com.alpha.galleryphoto.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout

abstract class ABaseView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    var view: View? = null

    init {
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        view = inflate(context, layout.id, this)
    }
}