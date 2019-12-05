package com.alpha.galleryphoto.presentation.photo.add

import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.IView


interface PhotoAddView : IView {

    fun endLoadPhoto(photo: Photo)

}