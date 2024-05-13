package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import java.util.Objects

class CategoriesFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories,container,false)
    }

    val categories = listOf(
        Category("sports",R.drawable.ball,R.string.sport,R.color.red),
        Category("technology",R.drawable.politics,R.string.technology,R.color.blue),
        Category("health",R.drawable.health,R.string.health,R.color.pink),
        Category("business",R.drawable.bussines,R.string.business,R.color.brown_orange),
        Category("general",R.drawable.environment,R.string.general,R.color.baby_blue),
        Category("science",R.drawable.science,R.string.science,R.color.yellow)
    )

    lateinit var recyclerView: RecyclerView
    val adapter = CategoriesAdapter(categories)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = requireView().findViewById(R.id.recycler_view_cat)
        recyclerView.adapter=adapter

        adapter.onItemClickListener = object :CategoriesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, category: Category) {
                onCategoryClickListener?.onCategoryClick(category)
            }
        }
    }

    var onCategoryClickListener:OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun onCategoryClick(category: Category)
    }

}