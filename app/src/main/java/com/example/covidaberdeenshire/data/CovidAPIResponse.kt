package com.example.covidaberdeenshire.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidApiResponse (
    @Expose
    @SerializedName("result")
    val result: CovidAPIResult
)