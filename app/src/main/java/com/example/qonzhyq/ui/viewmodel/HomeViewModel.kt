package com.example.qonzhyq.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: Repository): ViewModel() {

    val courseResponse: MutableLiveData<Response<List<Course>>> = MutableLiveData()

    fun getCourses() {
        viewModelScope.launch {
            val response = repository.getCourses()
            courseResponse.value = response
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser(Constants.token)
            Log.e("123", response.toString())
        }
    }
}