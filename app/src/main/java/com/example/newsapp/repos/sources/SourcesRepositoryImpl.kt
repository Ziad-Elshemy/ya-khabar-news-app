package com.example.newsapp.repos.sources

import com.example.newsapp.NetworkHandler
import com.example.newsapp.model.SourcesItem

class SourcesRepositoryImpl(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler
):SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourcesItem?>? {
        try {
            if (networkHandler.isOnline()){
                val result = sourcesOnlineDataSource.getSourcesByCategoryId(categoryId)
                sourcesOfflineDataSource.updateSources(result)
                return result
            }
            val res= sourcesOfflineDataSource.getSourcesByCategoryId(categoryId)
            return res
        }catch (ex:Exception){
//            throw ex
            return sourcesOfflineDataSource.getSourcesByCategoryId(categoryId)

        }
    }
}