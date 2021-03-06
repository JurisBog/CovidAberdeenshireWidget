package com.example.covidaberdeenshire.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidAPIResult (
    @Expose
    @SerializedName("records")
    var records: List<CovidApiRecord>
);