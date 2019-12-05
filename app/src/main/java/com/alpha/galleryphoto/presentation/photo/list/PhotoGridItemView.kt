package com.alpha.galleryphoto.presentation.photo.list

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.Layout
import com.alpha.galleryphoto.presentation.common.setVisibility
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.item_photo_grid.view.*


@Layout(id = R.layout.item_photo_grid)
class PhotoGridItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BasePhotoItem(context, attrs, defStyleAttr) {


    override fun bind(item: Photo, position: Int) {
        loadImage(item)
    }

    override fun loadImage(photo: Photo) {
        Glide.with(context)
            .asBitmap()
            .load(photo.small)
            .override(400, 400)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    itemPhoto.setImageBitmap(resource)
                }
            })
    }
}