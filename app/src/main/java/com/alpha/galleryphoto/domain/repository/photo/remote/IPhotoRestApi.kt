package com.alpha.galleryphoto.domain.repository.photo.remote


import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.remote.IRestApi
import io.reactivex.Observable
import okhttp3.MultipartBody

interface IPhotoRestApi : IRestApi {

    fun getListPhoto(request: Request): Observable<BaseResponse<MutableList<Photo>>>

    fun uploadImage(logs: MultipartBody.Part?): Observable<BaseResponse<Photo>>
}