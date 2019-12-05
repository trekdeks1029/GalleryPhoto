package com.alpha.galleryphoto.domain.repository.common.remote

import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.IRestClient


abstract class ABaseRestApi(private val restClient: IRestClient) : IRestApi {

    override fun cancelAllRequests() {
        restClient.cancelAllRequests()
    }
}
