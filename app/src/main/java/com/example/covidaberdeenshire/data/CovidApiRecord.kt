package com.example.covidaberdeenshire.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidApiRecord (
    @Expose
    @SerializedName("CumulativePositive")
    var total: Long,

    @Expose
    @SerializedName("CrudeRatePositive") var crudeTotal: Double,

    @Expose
    @SerializedName("CrudeRate7DayPositive") var crudeDaily: Double
) {
    fun getWeekly(): Double {
        return total/crudeTotal*crudeDaily;
    }
}