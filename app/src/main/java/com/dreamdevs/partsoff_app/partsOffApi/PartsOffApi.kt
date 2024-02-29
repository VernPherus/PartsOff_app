package com.dreamdevs.partsoff_app.partsOffApi

import com.dreamdevs.partsoff_app.partsOffModels.authModels.LoginResponse
import com.dreamdevs.partsoff_app.partsOffModels.authModels.RegisterResponse
import com.dreamdevs.partsoff_app.partsOffModels.categoryModels.Category
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PartsOffApi {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("phone") phone:String,
        @Field("password") password:String,
        @Field("password_confirmation") passwordConfirmation:String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("authenticate")
    fun authenticate(
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<LoginResponse>


    @GET("categories")
    suspend fun getCategories() : Response<List<Category>>


    @GET("products")
    suspend fun getProducts() : Product

}