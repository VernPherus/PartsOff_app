package com.dreamdevs.partsoff_app.partsOffModels.authModels

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)