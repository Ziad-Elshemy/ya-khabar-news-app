package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.ArticlesItem

class NewsAdapter(var items:List<ArticlesItem?>? = null) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position]
        holder.author.setText(item?.author)
        holder.title.setText(item?.title)
        holder.date_time.setText(item?.publishedAt)
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)

    }

    fun changeData(data:List<ArticlesItem?>?){
        items = data
        notifyDataSetChanged()
    }


    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.news_title)
        val image: ImageView = itemView.findViewById(R.id.news_image)
        val author: TextView = itemView.findViewById(R.id.author)
        val date_time: TextView = itemView.findViewById(R.id.date_time)
//        val prgress_bar: TextView = itemView.findViewById(R.id.news_progress_bar)
    }
}