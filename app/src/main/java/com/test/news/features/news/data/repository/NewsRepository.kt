package com.test.news.features.news.data.repository

import com.test.news.features.news.data.dao.NewsDao
import com.test.news.features.news.data.model.NewsWithImages
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class NewsRepository
@Inject
constructor(
    private val newsDao: NewsDao,
) {
    fun getNews(): Flow<List<NewsWithImages>> = newsDao.getNewsWithImages()
}
