package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newsapp.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("select * from sourcesitem")
    suspend fun getSources():List<SourcesItem?>

    @Query("select * from sourcesitem where category=:categoryId")
    suspend fun getSourcesByCategoryId(categoryId:String):List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources:List<SourcesItem?>?)

}