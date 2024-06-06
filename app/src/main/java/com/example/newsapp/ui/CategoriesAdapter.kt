package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(val categories:List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == isLeftSided)
                R.layout.item_category_left_sided
            else R.layout.item_category_right_sided,
            parent,false)
        return ViewHolder(view)
    }

    val isRightSided = 10
    val isLeftSided = 20
    override fun getItemViewType(position: Int): Int {
        if (position%2==0) return isLeftSided
        return isRightSided
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = categories[position]
        holder.title.setText(cat.titleId)
        holder.image.setImageResource(cat.imageId)
        holder.parent.setCardBackgroundColor(
            holder.parent.context.getColor(cat.backgroundColor)
        )
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


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val parent:MaterialCardView = itemView.findViewById(R.id.parent)
        val child:ConstraintLayout = itemView.findViewById(R.id.child)
        val title:TextView = itemView.findViewById(R.id.item_title)
        val image:ImageView=itemView.findViewById(R.id.item_image)
    }

}