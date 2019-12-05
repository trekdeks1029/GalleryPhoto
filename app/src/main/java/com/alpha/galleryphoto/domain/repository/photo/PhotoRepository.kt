package com.alpha.galleryphoto.domain.repository.photo

import android.content.Context
import android.net.Uri
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.BaseRepository
import com.alpha.galleryphoto.domain.repository.photo.remote.IPhotoRestApi
import com.alpha.galleryphoto.presentation.common.standartIO
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PhotoRepository(private val restApi: IPhotoRestApi) : BaseRepository(), IPhotoRepository {


    override fun list(request: Request): Observable<MutableList<Photo>> =
        restApi.getListPhoto(request).standartIO().map {
            it.data
        }

    override fun uploadPhoto(file: File): Observable<Photo> {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val photo = MultipartBody.Part.createFormData("file", file.name, requestFile)

        return restApi.uploadImage(photo).standartIO().map {
            it.data
        }
    }

    override fun createNewEmptyFile(context: Context): Uri {
        return Uri.fromFile(
            File(
                context.filesDir,
                System.currentTimeMillis().toString() + ".jpg"
            )
        )
    }

}