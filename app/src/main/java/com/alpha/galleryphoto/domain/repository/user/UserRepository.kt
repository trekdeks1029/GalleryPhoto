package com.alpha.galleryphoto.domain.repository.user

import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import com.alpha.galleryphoto.domain.repository.common.BaseRepository
import com.alpha.galleryphoto.domain.repository.user.local.IUserLocalStorage
import com.alpha.galleryphoto.domain.repository.user.remote.IUserRestApi
import com.alpha.galleryphoto.presentation.common.standartIO
import io.reactivex.Observable

class UserRepository(
    private val localStorage: IUserLocalStorage,
    private val restApi: IUserRestApi
) : BaseRepository(), IUserRepository {


    override fun getCurrentUser() = localStorage.getCurrentUser()

    override fun login(request: Request): Observable<BaseResponse<User>> {
        return restApi.login(request).standartIO()
    }

    override fun reg(user: User): Observable<BaseResponse<User>> {
        return restApi.reg(user).standartIO()
    }

}