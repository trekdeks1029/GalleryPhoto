package com.alpha.galleryphoto.presentation.photo.list

import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.IView


interface PhotoListView : IView {

    fun showPhotos(list: MutableList<Photo>)

}