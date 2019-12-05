package com.alpha.galleryphoto.presentation.photo.add

import com.alpha.galleryphoto.domain.repository.photo.IPhotoRepository
import com.alpha.galleryphoto.presentation.common.BasePresenter
import com.arellomobile.mvp.InjectViewState
import java.io.File
import javax.inject.Inject

@InjectViewState
class PhotoAddPresenter @Inject constructor(val repository: IPhotoRepository) :
    BasePresenter<PhotoAddView>() {

    var file: File? = null


    fun uploadPhoto() {
        if (file == null)
            return


        viewState.showLoadingProgress(true)


        addDisposable(repository.uploadPhoto(file!!).subscribe({
            viewState.endLoadPhoto(it)
            viewState.showLoadingProgress(false)
        }, {
            viewState.showLoadingProgress(false)
            getViewError(it)
        }))
    }
}