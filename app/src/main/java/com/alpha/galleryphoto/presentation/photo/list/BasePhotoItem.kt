package com.alpha.galleryphoto.presentation.photo.list

import android.content.Context
import android.util.AttributeSet
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.ABaseView
import com.alpha.galleryphoto.presentation.common.IListItemView


abstract class BasePhotoItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr),
    IListItemView<Photo> {


    abstract fun loadImage(photo: Photo)

}