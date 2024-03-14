package com.dreamdevs.partsoff_app.partsOffApi

import com.dreamdevs.partsoff_app.partsOffModels.checkoutModels.OrderItem
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("authenticate")
    fun     login(
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

    @FormUrlEncoded
    @POST("process-checkout")
    fun processCheckout(
        @Field("id") id : String,
        @Field("first_name") firstName : String,
        @Field("last_name") lastName : String,
        @Field("email") email : String,
        @Field("province") province : String,
        @Field("address") address : String,
        @Field("city") city : String,
        @Field("barangay") barangay : String,
        @Field("zip") zip : String,
        @Field("mobile") mobile : String,
        @Field("subtotal") subtotal : String,
        @Field("grand_total") grandTotal : String,
        @Field("order_items") orderItems : List<OrderItem>
    ) : Call<Void>

}