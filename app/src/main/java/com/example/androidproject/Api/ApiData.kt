package com.example.androidproject.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiData {

    companion object {

        val BASEURL = "https://jsonplaceholder.typicode.com/"

        fun getRetrofit(): Retrofit {
            var retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}