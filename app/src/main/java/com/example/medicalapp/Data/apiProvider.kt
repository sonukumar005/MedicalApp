package com.example.medicalapp.Data

import com.example.medicalapp.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiProvider {
    fun providerApi() = Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().build())
    .addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(apiService::class.java)
}