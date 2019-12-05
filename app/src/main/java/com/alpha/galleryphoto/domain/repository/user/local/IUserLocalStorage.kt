package com.alpha.galleryphoto.domain.repository.user.local

import com.alpha.galleryphoto.domain.model.data.User


interface IUserLocalStorage {

    fun getCurrentUser(): User?

    fun saveUser(user: User)

    fun deleteUser(user: User)
}