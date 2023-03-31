package com.example.qonzhyq.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.ui.viewmodel.LoginViewModel
import com.example.qonzhyq.ui.viewmodel.RegisterViewModel

class LoginViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}