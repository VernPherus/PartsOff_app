package com.dreamdevs.partsoff_app.partsOffApi

import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductDisplayData
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @FormUrlEncoded
    @POST("authenticate")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Void> // Update the return type to Void since there's no token

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation : String
    ): Call<Void>

    @GET("displayProducts")
    fun getProducts() : Call<List<ProductsData>>

    @GET("shop-getProduct/{title}")
    fun getProductData(
        @Path("title") title: String
    ) : Call<List<ProductDisplayData>>
}