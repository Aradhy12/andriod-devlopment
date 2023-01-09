package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.fuckin.News
import com.example.fuckin.NewsItemClicked
import com.example.fuckin.newslistadapter
import com.example.fuckin.singleton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), NewsItemClicked {


    private lateinit var madapter: newslistadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvnews.layoutManager= LinearLayoutManager(this)
        fetchData()
        madapter =newslistadapter(this)
        rvnews.adapter=madapter
    }
    private fun fetchData()
    {
        val url= "https://newsapi.org/v2/everything?q=tesla&from=2022-12-06&sortBy=publishedAt&apiKey=0552a96fa9db4a31a1b2c99ab89c0b7c"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {

                val newsJsonArray=it.getJSONArray("articles")//taking the json array from our api;
                val newsArray=ArrayList<News>()
                for( i in 0 until 50)
                {
                    val newJsonObject=newsJsonArray.getJSONObject(i)
                    val news=News(newJsonObject.getString("title"),newJsonObject.getString("author"),
                        newJsonObject.getString("url")
                        ,newJsonObject.getString("imageUrl"))
                    newsArray.add(news)



                }
                madapter.udatedNews(newsArray)

            },
            Response.ErrorListener {
            }
        )

        singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


// Access the RequestQueue through your singleton class.


    }

    override fun onItemClicked(item: News) {

    }

}