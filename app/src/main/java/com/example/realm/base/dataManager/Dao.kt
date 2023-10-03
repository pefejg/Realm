package com.example.realm.base.dataManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

public class Dao {
    private val mRealm = Realm.getDefaultInstance()
    private fun <T : RealmObject> RealmResults<T>.asLiveData() = BaseDataManager(this)

    open fun <T : RealmObject> save(tObjet: T) {
        mRealm.executeTransactionAsync {
            it.insertOrUpdate(tObjet)
        }
    }

    open fun <T : RealmObject> save(tObjet: List<T>) {
        mRealm.executeTransaction {
            for (t in tObjet) {
                it.insertOrUpdate(t)
            }
        }
    }

    open fun <T : RealmObject> get(tClass: Class<T>): LiveData<T>? {
        val mutableValue = MutableLiveData<T>()
        val result: LiveData<T> = mutableValue
        mutableValue.value = mRealm.copyFromRealm(mRealm.where(tClass).findFirst())
        return result
    }

    internal inline fun <reified T : RealmObject> getSync(): T? {
        val element = mRealm.where(T::class.java).findFirst() ?: return null
        val value =  mRealm.copyFromRealm(element)
        try {
            mRealm.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return value
    }

    open fun <T : RealmObject> getAll(tClass: Class<T>): LiveData<ArrayList<T>> {
        val mutableValue = MutableLiveData<ArrayList<T>>()
        val result: LiveData<ArrayList<T>> = mutableValue
        mutableValue.value = ArrayList()
        mutableValue.value!!.addAll(mRealm.copyFromRealm(mRealm.where(tClass).findAll()))
        return result
    }

    open fun <T: RealmObject> delete(tClass: Class<T>) {
        mRealm.executeTransaction {
            val result = it.where(tClass).findAll()
            result.deleteAllFromRealm()
        }
    }

    open fun deleteAll() {
        mRealm.executeTransaction {
            it.deleteAll()
        }
    }

    open fun closeRealm() {
        mRealm.close()
    }

}