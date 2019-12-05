package com.alpha.galleryphoto.domain.repository.user.remote

import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.rest.BaseResponse
import com.alpha.galleryphoto.domain.model.rest.Request
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface IUserRestApiService {

    @POST("/qm/api/auth.php")
    fun auth(@Body auth: Request): Observable<BaseResponse<User>>

    @POST("/qm/api/reg.php")
    fun reg(@Body user: User): Observable<BaseResponse<User>>

}