package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.ui.category.Category
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class NewsFragment:Fragment() {

    companion object{
        fun getInstance(category: Category): NewsFragment {
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
    lateinit var viewModel: NewsViewModel
    val adapter = NewsAdapter(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        subscribeToLiveData()
        viewModel.getNewsSources(category)

    }

    private fun subscribeToLiveData() {
        viewModel.sourcesLiveData.observe(viewLifecycleOwner){sources->
            showTabs(sources)
        }
        viewModel.newsListLiveData.observe(viewLifecycleOwner){
            showNews(it)
        }
        viewModel.showProgress.observe(viewLifecycleOwner){
            progressBar.isVisible = it
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner){message->
            Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
        }
    }

    private fun showNews(newsList: List<ArticlesItem?>?){
        adapter.changeData(newsList)
    }


    private fun initView() {
        progressBar = requireView().findViewById(R.id.progress_bar)
        tabLayout = requireView().findViewById(R.id.tab_layout)
        recyclerView = requireView().findViewById(R.id.news_recycler_view)
        recyclerView.adapter = adapter

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
                viewModel.loadNews(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                progressBar.isVisible = true
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                viewModel.loadNews(source)
            }

        })
        tabLayout.getTabAt(0)?.select()
    }



}