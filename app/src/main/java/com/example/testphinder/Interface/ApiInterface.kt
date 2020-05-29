package com.example.testphinder.Interface

import com.example.testphinder.POJO.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("initial_load")
    fun getload() : Call<DataModel>
}