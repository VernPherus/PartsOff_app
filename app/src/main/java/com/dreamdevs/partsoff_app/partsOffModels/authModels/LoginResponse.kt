package com.dreamdevs.partsoff_app.partsOffModels.authModels

data class LoginResponse(
    val message: String,
    val status : Boolean,
    val user: User
)