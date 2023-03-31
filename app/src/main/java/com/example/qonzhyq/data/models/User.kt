package com.example.qonzhyq.data.models

data class User (
    val id: Long? = null,
    val email: String? = null,
    val password: String? = null,
    var token: String? = null,
    var lessonId: Long? = null,
    var quizId: Long? = null,
    var courseId: Long? = null
)