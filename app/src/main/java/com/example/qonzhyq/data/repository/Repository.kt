package com.example.qonzhyq.data.repository


import com.example.qonzhyq.data.api.RetrofitInstance
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.models.Lesson
import com.example.qonzhyq.data.models.User
import com.example.qonzhyq.data.request.CredentialsRequest
import com.example.qonzhyq.data.response.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response

class Repository {
    suspend fun login(credentialsRequest:CredentialsRequest): Response<TokenResponse> {
       return RetrofitInstance.courseApi.login(credentialsRequest)
    }

    suspend fun register(credentialsRequest:CredentialsRequest): Response<TokenResponse> {
       return RetrofitInstance.courseApi.register(credentialsRequest)
    }

    suspend fun getCourses(): Response<List<Course>> {
       return RetrofitInstance.courseApi.getCourses()
    }

    suspend fun getUser(token: String):Response<User> {
        return RetrofitInstance.courseApi.getUser(token)
    }

    suspend fun findAllByCourseId(courseId:Long) :Response<List<Lesson>> {
        return RetrofitInstance.courseApi.findAllByCourseId(courseId)
    }

}