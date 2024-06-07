package com.example.newsapp.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constans
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.model.SourcesResponse
import com.example.newsapp.ui.category.Category
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsListLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val showProgress = MutableLiveData<Boolean>(false)
    val messageLiveData = MutableLiveData<String>()


    fun getNewsSources(category: Category) {
        // using coroutine
        viewModelScope.launch {
            showProgress.value = true
            try {

                val sourcesResponse =
                    ApiManager.getApis().getSources(Constans.API_KEY, category.id)
                showProgress.value = false
                sourcesLiveData.value = sourcesResponse.sources
                Log.e("News Sources Response: ", sourcesResponse.toString())

            } catch (ex: Exception) {
                showProgress.value = false
                messageLiveData.value = ex.localizedMessage
            }
        }

//        ApiManager.getApis().getSources(Constans.API_KEY,category.id)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    showProgress.value = false
//                    messageLiveData.value = t.localizedMessage
//                }
//
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    showProgress.value = false
//                    sourcesLiveData.value = response.body()?.sources
//                    Log.e("News Sources Response: ",response.body().toString())
//                }
//            })
    }

    fun loadNews(source: SourcesItem) {
        viewModelScope.launch {

            try {
                newsListLiveData.value = null
//        adapter.changeData(null)
                showProgress.value = true
                val newsResponse =
                    ApiManager.getApis().getNews(Constans.API_KEY, source.id.toString())
                showProgress.value = false
                newsListLiveData.value = newsResponse.articles
            } catch (ex: Exception) {
                messageLiveData.value = "Error Loading News"
                showProgress.value = false
            }

        }


//        ApiManager.getApis()
//            .getNews(Constans.API_KEY, source.id.toString())
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
////                    Toast.makeText(requireContext(),"Error Loading News", Toast.LENGTH_LONG).show()
//                    messageLiveData.value = "Error Loading News"
//                    showProgress.value = false
//                }
//
//
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    showProgress.value = false
//                    newsListLiveData.value = response.body()?.articles
//                }
//            })
    }


}