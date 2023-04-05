package com.example.Myapp
import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("v2/everything?domains=wsj.com&apiKey=0552a96fa9db4a31a1b2c99ab89c0b7c")

    fun getData(): Call<NewsResponse>
}