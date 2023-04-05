package com.example.Myapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(
    @Expose @SerializedName("title")
    val title:String,
    @Expose @SerializedName("author" )
    val author:String,
    @Expose @SerializedName("url" )
    val url:String,
    @Expose @SerializedName("urlToImage" )
    val imageUrl: String)


