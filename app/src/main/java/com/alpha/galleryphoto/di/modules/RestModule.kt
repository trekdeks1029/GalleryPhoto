package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.IRestClient
import com.alpha.galleryphoto.domain.repository.photo.remote.IPhotoRestApi
import com.alpha.galleryphoto.domain.repository.photo.remote.PhotoRestApi
import com.alpha.galleryphoto.domain.repository.user.remote.IUserRestApi
import com.alpha.galleryphoto.domain.repository.user.remote.UserRestApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun photoRestApi(restClient: IRestClient): IPhotoRestApi {
        return PhotoRestApi(restClient)
    }

    @Provides
    @Singleton
    fun userRestApi(restClient: IRestClient): IUserRestApi {
        return UserRestApi(restClient)
    }
}