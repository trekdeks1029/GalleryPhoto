package com.alpha.galleryphoto.domain.repository.user.remote

import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.remote.retrofit.IRestClient
import com.alpha.galleryphoto.domain.repository.common.remote.ABaseRestApi
import io.reactivex.Observable

class UserRestApi(restClient: IRestClient) : ABaseRestApi(restClient), IUserRestApi {

    private var service: IUserRestApiService = restClient.createService(IUserRestApiService::class.java)

    override fun login(request: Request): Observable<BaseResponse<User>> = service.auth(request)

    override fun reg(user: User): Observable<BaseResponse<User>> = service.reg(user)
}