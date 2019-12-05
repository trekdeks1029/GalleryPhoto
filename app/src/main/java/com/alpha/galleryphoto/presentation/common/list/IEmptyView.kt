package com.alpha.galleryphoto.presentation.common.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface IEmptyView {
    fun setText(@StringRes text: Int)

    fun setIcon(@DrawableRes icon: Int)
}