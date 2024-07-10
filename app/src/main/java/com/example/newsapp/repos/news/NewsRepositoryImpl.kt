package com.example.newsapp.repos.news

import com.example.newsapp.model.ArticlesItem

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource)
    : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        }catch (ex:Exception){
            throw ex
        }
    }
}