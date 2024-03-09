package com.dreamdevs.partsoff_app.partsOffModels.authModels

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("password") val password : String,
    @SerializedName("password_confirmation") val passwordConfirmation : String
)
