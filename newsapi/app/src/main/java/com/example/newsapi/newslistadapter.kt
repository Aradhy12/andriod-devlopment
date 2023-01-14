package com.example.fuckin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class newslistadapter(private val listerner:NewsItemClicked): RecyclerView.Adapter<newsviewholder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsviewholder {
        val view=LayoutInflater.from(parent.context).inflate( R.layout.newslayout,parent,false)
        val viewHolder=newsviewholder(view)
        view.setOnClickListener{
            listerner.onItemClicked(items[viewHolder.adapterPosition] )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: newsviewholder, position: Int) {
        val currentItem=items[position]
        holder.titleview.text=currentItem.title
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun udatedNews(updatenews:ArrayList<News>)
    {
        items.clear()
        items.addAll(updatenews)
        notifyDataSetChanged()//this called all the three function reload the recycler view
    }
}
class newsviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)//is me sirf layout file me ek
//textview hai,ye newviewholder chaihiye adapter ko ..
{
    val titleview:TextView=itemView.findViewById(R.id.tvnw)
}
interface  NewsItemClicked
{
    fun onItemClicked(item:News)
}