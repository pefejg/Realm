package com.example.realm.main.background.repository

import com.example.realm.base.BaseRepository
import com.example.realm.main.background.response.Result
import com.example.realm.main.background.datasource.MainHomeDataSource
import com.example.realm.main.background.response.Planets
import retrofit2.Response

class MainHomeRepository(val mDataSource: MainHomeDataSource): BaseRepository() {

    suspend fun getPlanets(): Response<Planets>?{
        return mDataSource.getPlanets()
    }

    suspend fun getPlanetById(id: String): Response<Result>?{
        return mDataSource.searchById(id)
    }
}