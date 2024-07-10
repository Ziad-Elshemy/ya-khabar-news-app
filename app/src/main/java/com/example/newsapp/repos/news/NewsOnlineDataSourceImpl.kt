package com.example.newsapp.repos.news

import com.example.newsapp.Constans
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.ArticlesItem

class NewsOnlineDataSourceImpl(val webServices: WebServices): NewsOnlineDataSource {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getNews(Constans.API_KEY,sourceId)
            return result.articles
        }catch (ex:Exception){
            throw ex
        }
    }
}