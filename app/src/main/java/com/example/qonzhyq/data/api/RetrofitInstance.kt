package com.example.qonzhyq.data.api

import com.example.qonzhyq.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val courseApi: CourseApi by lazy {
        retrofit.create(CourseApi::class.java)
    }
}