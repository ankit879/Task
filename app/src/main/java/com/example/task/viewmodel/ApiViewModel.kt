package com.example.ankittask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ankittask.errorhandling.ResultState
import com.example.ankittask.model.DataModel
import com.example.ankittask.repogistory.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(val repo: Repo) : ViewModel() {
    private var _getRepo: MutableLiveData<ResultState<DataModel>> = MutableLiveData()
    val getRepo: LiveData<ResultState<DataModel>> = _getRepo
     var currentPage = 1
    var   isLoading=false
    var isLastpage=false
    fun getRepo(page:Int,resultsize:Int) {
        if (isLastpage || isLoading)return
        isLoading=true
        _getRepo.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = repo.getRepo(page,resultsize)
                if (response.isSuccessful && response.body() != null) {
                    _getRepo.postValue(ResultState.Success(response.body()!!))
                    currentPage++
                    isLoading=false
                } else {
                    isLastpage=true
                    _getRepo.postValue(ResultState.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                _getRepo.postValue(ResultState.Error(e.message.toString()))

            }


        }
    }
}