package com.example.realm.base

import android.util.Log
import com.example.realm.base.dataManager.Dao
import com.example.realm.main.background.model.User


open class BaseRepository {

    val dao = Dao()



    fun getNullableUser(): User?{
        var userLocal: User? = null
        try {
            userLocal = dao.get(User::class.java)!!.value!!
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("BaseRepository", "Could not get local user object, operation aborted")
        }
        return userLocal
    }

}