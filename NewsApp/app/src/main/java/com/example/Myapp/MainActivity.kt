package com.example.Myapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog


import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.fuckin.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsViewHolder.NewsItemClicked {


    private lateinit var madapter:  newslistadapter
    val url= "https://newsapi.org/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvnews.layoutManager= LinearLayoutManager(this)
         fetchData2()
        madapter =newslistadapter(this)
        rvnews.adapter=madapter
    }
    private fun fetchData()
    {


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {

                   val newArray =Gson().fromJson<NewsResponse>(it.toString(),NewsResponse::class.java)

                  madapter.udatedNews(newArray.articleData)

            },
            Response.ErrorListener {

 
            }
        )
        singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
    private fun fetchData2()
    {
        val retrofitBuilder=Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.
            create()).
            baseUrl(url).build()
            .create(apiInterface::class.java)
        val retrofitData=retrofitBuilder.getData()//created in interface
        retrofitData.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {


                response.body()?.articleData?.let { madapter.udatedNews(it) }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
           Log.e("abhi","error",t)
           callintent()

            }
        })

    }
    fun callintent()
    {  val intent=Intent(this,JsonError::class.java)
        startActivity(intent)

    }

    override fun onItemClicked(item: News) {
        val url =item.url
        if(url.isEmpty())
        {
            AlertDialog.Builder(this)
                .setTitle("uri invalid")
                .setMessage("give valid url")
                .setPositiveButton("OK") {dialog,_->dialog.dismiss()}
                .setCancelable(false)
                .show()
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)


    }

}