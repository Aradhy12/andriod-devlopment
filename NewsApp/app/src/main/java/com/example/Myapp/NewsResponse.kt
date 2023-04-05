package com.example.Myapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(@Expose @SerializedName("articles" ) val articleData: List<News>)

