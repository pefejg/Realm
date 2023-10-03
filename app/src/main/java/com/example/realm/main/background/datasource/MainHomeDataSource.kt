package com.example.realm.main.background.datasource

import com.example.realm.main.background.ApiService
import com.example.realm.main.background.response.Result
import com.example.realm.main.background.response.Planets
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainHomeDataSource {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/planets/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    suspend fun getPlanets(): Response<Planets>? {
        val call: Response<Planets> = getRetrofit().create(ApiService::class.java).getPlanets("https://swapi.dev/api/planets/")
         return if (call.isSuccessful)
             call
         else
             null
    }

    suspend fun searchById(id: String): Response<Result>?{
        val call: Response<com.example.realm.main.background.response.Result> = getRetrofit().create(ApiService::class.java).getPlanetById("$id")
        return if (call.isSuccessful)
            call
        else
            null
    }
}