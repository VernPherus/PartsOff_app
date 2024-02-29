package com.dreamdevs.partsoff_app.partsOffApi

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val AUTH = "Basic " + Base64.encodeToString("email:password".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL = "http://64.23.185.162/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", AUTH)
                    .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: PartsOffApi by lazy {
         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(PartsOffApi::class.java)
    }
}