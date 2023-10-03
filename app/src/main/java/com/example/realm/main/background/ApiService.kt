package com.example.realm.main.background

import com.example.realm.main.background.response.Planets
import com.example.realm.main.background.response.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPlanets(@Url url: String): Response<Planets>

    @GET()
   suspend fun getPlanetById(@Url url: String): Response<Result>
}