package com.example.platenumber

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://whistlecrowd.com/app/api/v1/test/"

    var interceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();

    fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
