package com.example.testphinder.request

import com.google.gson.annotations.SerializedName

class InitialLoadRequest (
    @SerializedName("userId") val userId: String,
    @SerializedName("env") val env: String,
    @SerializedName("os") val os: String
   )
