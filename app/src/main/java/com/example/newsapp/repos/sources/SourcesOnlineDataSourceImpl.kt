package com.example.newsapp.repos.sources

import com.example.newsapp.Constans
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.SourcesItem

class SourcesOnlineDataSourceImpl(val webServices: WebServices):SourcesOnlineDataSource {
    override suspend fun getSourcesByCategoryId(categoryId: String): List<SourcesItem?>? {
        try {
            val result = webServices.getSources(Constans.API_KEY,categoryId)
            return result.sources
        }catch (ex:Exception){
            throw ex
        }
    }
}