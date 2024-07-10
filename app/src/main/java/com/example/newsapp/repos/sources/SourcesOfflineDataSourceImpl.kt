package com.example.newsapp.repos.sources

import com.example.newsapp.database.MyDatabase
import com.example.newsapp.model.SourcesItem

class SourcesOfflineDataSourceImpl(val myDatabase: MyDatabase):SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDatabase.sourcesDao().updateSources(sources)
    }

    override suspend fun getSourcesByCategoryId(categoryId: String): List<SourcesItem> {
        val result =  myDatabase.sourcesDao().getSourcesByCategoryId(categoryId)
        return result
    }
}