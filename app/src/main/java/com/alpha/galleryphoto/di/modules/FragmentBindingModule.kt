package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.di.scopes.FragmentScope
import com.alpha.galleryphoto.presentation.photo.list.PhotoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun photoListFragment(): PhotoListFragment

}