package com.test.news.features.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.news.features.news.data.dao.NewsDao
import com.test.news.features.news.data.model.Image
import com.test.news.features.news.data.model.News

@Database(
    entities = [News::class, Image::class],
    version = 1,
    exportSchema = false,
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
