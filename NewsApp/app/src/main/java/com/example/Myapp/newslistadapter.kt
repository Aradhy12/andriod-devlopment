package com.example.Myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuckin.R

//listner is an instance of interface
class newslistadapter(private val listerner: NewsViewHolder.NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.newlayout,parent,false)
        val viewHolder=NewsViewHolder(view,listerner)
        view.setOnClickListener{
          listerner.onItemClicked(items[viewHolder.adapterPosition] )
      }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
      val currentItem=items[position]
       holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
     return items.size
    }
  fun udatedNews(updatenews:List<News>)
  {
      items.clear()
      items.addAll(updatenews)
      notifyDataSetChanged()//this called all the three function reload the recycler view
  }
}
