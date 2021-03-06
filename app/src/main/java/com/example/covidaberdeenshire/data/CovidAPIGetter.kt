package com.example.covidaberdeenshire.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val url = "https://www.opendata.nhs.scot/api/3/action/datastore_search/";
private const val base_url = "https://www.opendata.nhs.scot/api/3/action/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(base_url)
    .build();

interface CovidAPIGetterService {
    @GET("datastore_search?resource_id=427f9a25-db22-4014-a3bc-893b68243055&limit=1&sort=_id%20desc&q=Aberdeenshire")
    fun getAberdeenshireCases(): Call<CovidApiResponse>
}

object CovidAPI {
    val retrofitService : CovidAPIGetterService by lazy {
        retrofit.create(CovidAPIGetterService::class.java)
    }
}