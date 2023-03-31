package com.example.qonzhyq.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.data.request.CredentialsRequest
import com.example.qonzhyq.data.response.TokenResponse
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class RegisterViewModel(private val repository: Repository): ViewModel() {

    val tokenResponse: MutableLiveData<Response<TokenResponse>> = MutableLiveData()

    fun register(email:String, password: String) {
        viewModelScope.launch {
            val response = repository.register(CredentialsRequest(email, password))
            if (response?.isSuccessful == true) {
                tokenResponse.value = response
            }
        }
    }
}