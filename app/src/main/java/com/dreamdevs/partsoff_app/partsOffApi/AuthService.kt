package com.dreamdevs.partsoff_app.partsOffApi

import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("authenticate")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Void> // Update the return type to Void since there's no token

    @GET("displayProducts")
    fun getProducts() : Response<List<Products>>
}