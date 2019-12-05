package com.alpha.galleryphoto

import android.app.Activity
import android.app.Application
import com.alpha.galleryphoto.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import javax.inject.Inject

class App : Application(), HasActivityInjector {


    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .create(this)
            .build()
            .inject(this)


        initRealm()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }


    private fun initRealm(){
        Realm.init(this)

    }
}