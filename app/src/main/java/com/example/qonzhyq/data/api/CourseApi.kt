package com.example.qonzhyq.data.api

import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.models.Lesson
import com.example.qonzhyq.data.models.User
import com.example.qonzhyq.data.request.CredentialsRequest
import com.example.qonzhyq.data.response.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CourseApi {

    @POST("/registration/registration.html")
    suspend fun login(
        @Body credentialsRequest: CredentialsRequest
    ): Response<TokenResponse>

    @POST("/registration/registration.html")
    suspend fun register(
        @Body credentialsRequest: CredentialsRequest
    ): Response<TokenResponse>


    @GET("/course/all")
    suspend fun getCourses(): Response<List<Course>>

    @GET("/student/home.html")
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): Response<User>


    @GET("lesson/by-course-id")
    suspend fun findAllByCourseId(
        @Query("courseId") courseId: Long
    ):Response<List<Lesson>>

}