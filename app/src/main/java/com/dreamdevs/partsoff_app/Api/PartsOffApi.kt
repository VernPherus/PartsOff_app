package com.dreamdevs.partsoff_app.Api

import com.dreamdevs.partsoff_app.userclasses.User
import com.dreamdevs.partsoff_app.partsOffClasses.categoryClasses.Category
import com.dreamdevs.partsoff_app.partsOffClasses.productClasses.Product
import retrofit2.http.GET
import retrofit2.Response

interface PartsOffApi {

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>


    @GET("/categories")
    suspend fun getCategories() : Response<List<Category>>


    @GET("/products")
    suspend fun getProducts() : Response<List<Product>>

}