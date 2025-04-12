package com.example.ankittask.repogistory

import android.content.Context
import com.example.ankittask.api.ApiService
import com.example.ankittask.model.DataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor(private val apiService: ApiService) {

    suspend fun getRepo(page:Int,resultsize:Int): Response<DataModel> {
        return apiService.getrepo(page,resultsize)
    }
}
