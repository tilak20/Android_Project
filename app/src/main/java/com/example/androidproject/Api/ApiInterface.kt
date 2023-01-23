package com.example.androidproject.Api

import com.example.androidproject.ModelData.ApiMD
import com.example.androidproject.ModelData.ApiMDItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos")
    fun getData() : Call<List<ApiMDItem>>
}