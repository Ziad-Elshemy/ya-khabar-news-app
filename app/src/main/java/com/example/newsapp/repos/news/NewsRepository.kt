package com.example.newsapp.repos.news

import com.example.newsapp.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourceId: String):List<ArticlesItem?>?
}
interface NewsOnlineDataSource {
    suspend fun getNewsBySourceId(sourceId: String):List<ArticlesItem?>?
}
