package com.example.realm.base.dataManager

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.RealmResults

open class BaseDataManager<T: RealmObject> (private val realmResults: RealmResults<T>): LiveData<RealmResults<T>>() {
    private val listener = RealmChangeListener<RealmResults<T>> { results ->
        value = results
    }

    override fun onActive() {
        realmResults.addChangeListener(listener)
    }

    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }

}