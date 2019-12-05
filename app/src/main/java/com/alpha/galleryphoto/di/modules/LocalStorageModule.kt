package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.domain.repository.user.local.IUserLocalStorage
import com.alpha.galleryphoto.domain.repository.user.local.UserLocalStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun userLocalStorage(): IUserLocalStorage {
        return UserLocalStorage()
    }
}