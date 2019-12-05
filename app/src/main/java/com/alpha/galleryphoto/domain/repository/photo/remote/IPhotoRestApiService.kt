package com.alpha.galleryphoto.domain.repository.photo.remote

import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IPhotoRestApiService {

    @POST("/photoGallery/api/getPhotos.php")
    fun list(@Body request: Request): Observable<BaseResponse<MutableList<Photo>>>

    @Multipart
    @POST("/photoGallery/api/newPhoto.php")
    fun uploadImage(@Part logs: MultipartBody.Part?): Observable<BaseResponse<Photo>>

}