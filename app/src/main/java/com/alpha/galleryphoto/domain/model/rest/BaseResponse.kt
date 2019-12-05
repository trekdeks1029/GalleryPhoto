package com.alpha.galleryphoto.domain.model.rest


data class BaseResponse<D>(var error: Boolean, var code: Int, var data: D? = null, var message: String?)