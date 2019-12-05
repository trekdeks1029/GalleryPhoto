package com.alpha.galleryphoto.domain.repository.user

import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import io.reactivex.Observable

interface IUserRepository {
    fun getCurrentUser(): User?

    fun login(request: Request): Observable<BaseResponse<User>>

    fun reg(user: User): Observable<BaseResponse<User>>
}