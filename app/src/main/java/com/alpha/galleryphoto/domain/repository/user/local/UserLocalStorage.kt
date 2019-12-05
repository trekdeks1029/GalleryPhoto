package com.alpha.galleryphoto.domain.repository.user.local

import com.alpha.galleryphoto.domain.model.data.User
import com.alpha.galleryphoto.domain.model.data.UserDb
import com.alpha.galleryphoto.domain.repository.common.local.BaseLocalStorage
import com.alpha.galleryphoto.utils.Const
import io.realm.Realm

class UserLocalStorage : BaseLocalStorage(), IUserLocalStorage {

    override fun getCurrentUser(): User? {

        Realm.getDefaultInstance().use { realm ->
            val user =
                realm.where(UserDb::class.java).equalTo("my", Const.IS_MY_ACCOUNT).findFirst()
            return if (user != null) User(realm.copyFromRealm(user)) else null
        }
    }

    override fun saveUser(user: User) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                it.copyToRealmOrUpdate(UserDb(user))
            }
        }
    }

    override fun deleteUser(user: User) {

    }
}