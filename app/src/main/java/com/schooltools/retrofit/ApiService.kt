package com.schooltools.retrofit

import com.schooltools.retrofit.dataClasses.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://google-news13.p.rapidapi.com/"
private  val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface ApiService {
    @Headers(
        "x-rapidapi-host: google-news13.p.rapidapi.com",
        "x-rapidapi-key: 49bc23d647msh2b265f2e72bef20p10fafejsn1a1207f8d031"
    )
    @GET("business?lr=en-US")
    suspend fun fetchData() : News
}
object Api{
    val retrofitService :ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}