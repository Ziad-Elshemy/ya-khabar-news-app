package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Constans
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.model.SourcesResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment:Fragment() {

    companion object{
        fun getInstance(category:Category):NewsFragment{
            val fragment = NewsFragment()
            fragment.category=category
            return fragment
        }
    }
    lateinit var category: Category

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container, false)
    }

    lateinit var progressBar:ProgressBar
    lateinit var tabLayout:TabLayout
    lateinit var recyclerView: RecyclerView
    var adapter = NewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getNewsServices()

    }

    private fun initView() {
        progressBar = requireView().findViewById(R.id.progress_bar)
        tabLayout = requireView().findViewById(R.id.tab_layout)
        recyclerView = requireView().findViewById(R.id.news_recycler_view)
        recyclerView.adapter = adapter

    }

    private fun getNewsServices() {
        ApiManager.getApis().getNewsSources(Constans.API_KEY,category.id)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible=false
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    showTabs(response.body()?.sources)
                    Log.e("News Sources Response: ",response.body().toString())
                }
            })
    }

    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach{
            item ->
            val tab = tabLayout.newTab()
            tab.tag = item
            tab.text = item?.name
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val source = sources?.get(tab?.position?:0)
                val source = tab?.tag as SourcesItem
                loadNews(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                progressBar.isVisible = true
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                loadNews(source)
            }

        })
        tabLayout.getTabAt(0)?.select()
    }

    fun loadNews(source:SourcesItem){
        adapter.changeData(null)
        progressBar.isVisible = true
        ApiManager.getApis()
            .getNews(Constans.API_KEY,source.id.toString())
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Toast.makeText(requireContext(),"Error Loading News",Toast.LENGTH_LONG).show()
                    progressBar.isVisible = false
                }


                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar.isVisible = false
                    adapter.changeData(response.body()?.articles)
                }
            })
    }


}