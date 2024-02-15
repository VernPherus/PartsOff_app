package com.dreamdevs.partsoff_app.Api

import com.dreamdevs.partsoff_app.userclasses.User
import retrofit2.http.GET
import retrofit2.Response

interface PartsOffApi {

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>

}