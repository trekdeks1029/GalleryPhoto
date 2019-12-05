package com.alpha.galleryphoto.domain.repository.photo.remote

import android.util.Log
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.IRestClient
import com.alpha.galleryphoto.domain.repository.common.remote.ABaseRestApi
import io.reactivex.Observable
import okhttp3.MultipartBody

class PhotoRestApi(restClient: IRestClient) : ABaseRestApi(restClient), IPhotoRestApi {

    private var service: IPhotoRestApiService = restClient.createService(IPhotoRestApiService::class.java)


    override fun getListPhoto(request: Request): Observable<BaseResponse<MutableList<Photo>>>{
        return service.list(request)
    }

    override fun uploadImage(logs: MultipartBody.Part?): Observable<BaseResponse<Photo>> = service.uploadImage(logs)
}