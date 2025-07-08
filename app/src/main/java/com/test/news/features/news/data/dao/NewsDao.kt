package com.test.news.features.news.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.test.news.features.news.data.model.NewsWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Transaction
    @Query("SELECT * FROM news")
    fun getNewsWithImages(): Flow<List<NewsWithImages>>
}
