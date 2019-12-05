package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.domain.repository.photo.IPhotoRepository
import com.alpha.galleryphoto.domain.repository.photo.PhotoRepository
import com.alpha.galleryphoto.domain.repository.photo.remote.IPhotoRestApi
import com.alpha.galleryphoto.domain.repository.user.IUserRepository
import com.alpha.galleryphoto.domain.repository.user.UserRepository
import com.alpha.galleryphoto.domain.repository.user.local.IUserLocalStorage
import com.alpha.galleryphoto.domain.repository.user.remote.IUserRestApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(LocalStorageModule::class), (RestModule::class)])
class RepoModule {

    @Provides
    @Singleton
    fun postRepository(restApi: IPhotoRestApi): IPhotoRepository {
        return PhotoRepository(restApi)
    }

    @Provides
    @Singleton
    fun userRepository(userLocalStorage: IUserLocalStorage, restApi: IUserRestApi): IUserRepository {
        return UserRepository(userLocalStorage, restApi)
    }
}