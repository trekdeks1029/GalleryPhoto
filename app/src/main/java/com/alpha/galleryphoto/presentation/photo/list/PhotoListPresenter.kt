package com.alpha.galleryphoto.presentation.photo.list

import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.photo.IPhotoRepository
import com.alpha.galleryphoto.presentation.common.BasePresenter
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

@InjectViewState
class PhotoListPresenter @Inject constructor(val repository: IPhotoRepository) :
    BasePresenter<PhotoListView>() {

    private var offset = 0
    private val count = 20

    private val list: MutableList<Photo> = mutableListOf()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        getPhotos()
    }

    private fun getPhotos() {
        addDisposable(
            repository.list(Request(count, offset)).subscribe({ photos ->
                list.addAll(photos)
                viewState.showLoadingProgress(false)
                viewState.showPhotos(list)
            }, {
                getViewError(it)
            })
        )
    }
    fun clearData(){
        list.clear()
        offset = 0
        getPhotos()
    }
}