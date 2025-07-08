package com.test.news.features.news.domain.repository

import com.test.news.features.news.data.repository.NewsRepository
import com.test.news.features.news.domain.model.NewsItemsModelMapper
import com.test.news.features.news.domain.model.NewsModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNewsUseCase
@Inject
constructor(
    private val newsRepository: NewsRepository,
    private val newsItemsModelMapper: NewsItemsModelMapper,
) {
    operator fun invoke(): Flow<List<NewsModel>> = newsRepository.getNews().map {
        newsItemsModelMapper.map(it)
    }
}
