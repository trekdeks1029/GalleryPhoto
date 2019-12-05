package com.alpha.galleryphoto.domain.repository.common.remote.retrofit

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>): S

    fun cancelAllRequests()
}