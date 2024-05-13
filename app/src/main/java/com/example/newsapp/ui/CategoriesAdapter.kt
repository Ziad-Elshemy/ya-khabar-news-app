package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class CategoriesAdapter(val categories:List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById(R.id.item_title)
        val image:ImageView=itemView.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = categories[position]
        holder.title.setText(cat.titleId)
        holder.image.setImageResource(cat.imageId)
        holder.itemView.setBackgroundResource(cat.backgroundColor)
        onItemClickListener.let {
            holder.itemView.setOnClickListener{
                onItemClickListener?.onItemClick(position,cat)
            }
        }
    }

    var onItemClickListener :OnItemClickListener? = null
    interface OnItemClickListener{
        fun onItemClick(position: Int,category: Category)
    }
}