package com.example.qonzhyq.data.request

import com.google.gson.annotations.SerializedName

data class CredentialsRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)