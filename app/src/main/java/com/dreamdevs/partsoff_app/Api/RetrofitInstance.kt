package com.dreamdevs.partsoff_app.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: PartsOffApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://64.23.185.162/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PartsOffApi::class.java)
    }
}