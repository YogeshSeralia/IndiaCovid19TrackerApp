package com.covid19.app.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val covid19APIService: Covid19APIService by lazy {
        Retrofit.Builder().baseUrl("https://api.covid19india.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Covid19APIService::class.java)
    }
}