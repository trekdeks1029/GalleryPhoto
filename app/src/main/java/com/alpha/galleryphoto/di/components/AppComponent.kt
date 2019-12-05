package com.alpha.galleryphoto.di.components

import android.app.Application
import com.alpha.galleryphoto.App
import com.alpha.galleryphoto.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ActivitiesBindingModule::class,
            FragmentBindingModule::class,
            LocalStorageModule::class,
            NetModule::class,
            RepoModule::class,
            RestModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }
}