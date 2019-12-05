package com.alpha.galleryphoto.di.modules

import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.HeaderInterceptor
import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.IRestClient
import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.RestClient
import com.alpha.galleryphoto.domain.repository.user.local.IUserLocalStorage
import com.alpha.galleryphoto.utils.Const
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    companion object {
        const val API_URL = "http://alverions.ru/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: Interceptor, headerInterceptor: HeaderInterceptor): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()
        okClientBuilder.connectTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)

        //if (BuildConfig.DEBUG)
            okClientBuilder.addInterceptor(logger)

        return okClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun loggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun headerInterceptor(localStorage: IUserLocalStorage): HeaderInterceptor {
        return HeaderInterceptor(localStorage)
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create()
    }

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient, gson: Gson): IRestClient {
        return RestClient(okHttpClient, gson, API_URL)
    }
}