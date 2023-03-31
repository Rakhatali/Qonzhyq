package com.example.qonzhyq.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qonzhyq.data.models.User
import com.example.qonzhyq.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel(private val repository: Repository): ViewModel() {

    val userResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun getUser(token:String) {
        viewModelScope.launch {
            val response = repository.getUser(token)
            userResponse.value = response
        }
    }
}