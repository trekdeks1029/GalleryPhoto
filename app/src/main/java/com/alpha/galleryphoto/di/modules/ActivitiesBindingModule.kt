package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.di.scopes.ActivityScope
import com.alpha.galleryphoto.presentation.MainActivity
import com.alpha.galleryphoto.presentation.photo.add.PhotoAddActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun photoAddActivity(): PhotoAddActivity

}