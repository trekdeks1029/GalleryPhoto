package com.alpha.galleryphoto.presentation.common.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.presentation.common.ABaseView
import com.alpha.galleryphoto.presentation.common.Layout
import kotlinx.android.synthetic.main.view_simple_empty.view.*

@Layout(id = R.layout.view_simple_empty)
open class SimpleEmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IEmptyView {

    override fun setText(text: Int) {
        if (tvNotDataFoundMessage != null)
            tvNotDataFoundMessage.setText(text)
    }

    override fun setIcon(icon: Int) {
        if (ivNotDataFound != null)
            ivNotDataFound.setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

    fun setIcon(icon: Drawable?) {
        ivNotDataFound.setImageDrawable(icon)
    }
}