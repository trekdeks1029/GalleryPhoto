package com.alpha.galleryphoto.domain.repository.common.local

import io.realm.Realm
import io.realm.RealmModel

abstract class BaseLocalStorage {


    fun <T : RealmModel> saveChanges(realmObject: T, realm: Realm): T {
        realm.beginTransaction()
        val result = realm.copyFromRealm(realm.copyToRealmOrUpdate(realmObject))
        realm.commitTransaction()
        return result
    }
}