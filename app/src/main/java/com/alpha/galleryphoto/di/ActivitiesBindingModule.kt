package com.alpha.galleryphoto.di

import com.alpha.galleryphoto.di.scopes.ActivityScope
import com.alpha.galleryphoto.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}