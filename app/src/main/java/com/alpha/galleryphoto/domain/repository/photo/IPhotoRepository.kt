package com.alpha.galleryphoto.domain.repository.photo

import android.content.Context
import android.net.Uri
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.domain.model.rest.Request
import io.reactivex.Observable
import java.io.File


interface IPhotoRepository {

    fun list(request: Request): Observable<MutableList<Photo>>

    fun uploadPhoto(file: File): Observable<Photo>

    fun createNewEmptyFile(context: Context): Uri

}