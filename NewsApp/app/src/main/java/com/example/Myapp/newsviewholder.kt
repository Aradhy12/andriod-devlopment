package com.example.Myapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.fuckin.R

class NewsViewHolder(itemView: View,listener:NewsItemClicked) : RecyclerView.ViewHolder(itemView) {
    var currentItem:News?=null
    init {
        itemView.setOnClickListener {
            val c=currentItem
            if(c!=null)
             listener.onItemClicked(c)
        }




    }
    val progressBar:ProgressBar=itemView.findViewById(R.id.ProgBr)
    val titleview: TextView = itemView.findViewById(R.id.tvnw)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
    fun bind(data: News) {
        progressBar.visibility=View.VISIBLE
        currentItem = data

        titleview.text = currentItem?.title ?: ""
        author.text = currentItem?.author ?: ""
        Glide.with(itemView.context).load(currentItem?.imageUrl).listener(object:RequestListener<Drawable>
        {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("Imagef","Error")
                progressBar.visibility=View.GONE

                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility=View.GONE
                return false

            }

        }).into(image)

    }
    interface NewsItemClicked {
        fun onItemClicked(item: News)
    }
}

