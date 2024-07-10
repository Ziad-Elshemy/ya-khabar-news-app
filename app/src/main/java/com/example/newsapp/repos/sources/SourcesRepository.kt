package com.example.newsapp.repos.sources

import com.example.newsapp.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(categoryId:String):List<SourcesItem?>?
}
interface SourcesOnlineDataSource {
    suspend fun getSourcesByCategoryId(categoryId:String):List<SourcesItem?>?
}

interface SourcesOfflineDataSource {
    suspend fun updateSources(sources:List<SourcesItem?>?)
    suspend fun getSourcesByCategoryId(categoryId:String):List<SourcesItem>
}