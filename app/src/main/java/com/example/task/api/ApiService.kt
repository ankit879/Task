package com.example.ankittask.api

import com.example.ankittask.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users/google/repos")
    suspend fun getrepo(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): Response<DataModel>
}