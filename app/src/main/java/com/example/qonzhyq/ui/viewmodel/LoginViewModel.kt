package com.example.qonzhyq.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.data.request.CredentialsRequest
import com.example.qonzhyq.data.response.TokenResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: Repository): ViewModel() {

    val tokenResponse: MutableLiveData<Response<TokenResponse>> = MutableLiveData()

    fun login(email:String, password: String) {
        viewModelScope.launch {
            val response = repository.login(CredentialsRequest(email, password))
            if (response?.isSuccessful == true) {
                tokenResponse.value = response
            }
        }
    }
}