package com.example.qonzhyq.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.models.Lesson
import com.example.qonzhyq.data.models.User
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class MyCourseViewModel(private val repository: Repository): ViewModel() {

    val lessonsResponse: MutableLiveData<Response<List<Lesson>>> = MutableLiveData()

    fun getLesson(courseId: Long) {
        viewModelScope.launch {
            val response = repository.findAllByCourseId(courseId)
            lessonsResponse.value = response
            Log.e("123", response.toString())
        }
    }
}