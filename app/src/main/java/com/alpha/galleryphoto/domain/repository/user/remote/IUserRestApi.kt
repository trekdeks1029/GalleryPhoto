package com.alpha.galleryphoto.domain.repository.user.remote


import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.remote.IRestApi
import io.reactivex.Observable

interface IUserRestApi : IRestApi {

    fun login(request: Request): Observable<BaseResponse<User>>

    fun reg(user: User): Observable<BaseResponse<User>>
}