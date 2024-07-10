package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.SourcesItem

@Database(entities =  [SourcesItem::class] , version = 1 , exportSchema = false)
abstract class MyDatabase:RoomDatabase() {

    abstract fun sourcesDao():SourcesDao

    companion object {
        const val DATABASE_NAME = "news_database"
        var database : MyDatabase?=null

        fun init(context:Context){
            if (database == null){
                database = Room.databaseBuilder(
                    context,MyDatabase::class.java, DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }
        }
        fun getInstance():MyDatabase{
            return database!!
        }
    }

}