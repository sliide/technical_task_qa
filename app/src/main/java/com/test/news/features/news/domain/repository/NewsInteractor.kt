package com.test.news.features.news.domain.repository

import com.test.news.features.news.domain.model.NewsModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

class NewsInteractor
@Inject
constructor(
    private val getNewsUseCase: GetNewsUseCase,
) {
    fun getNews(): Flow<NewsResult> = getNewsUseCase()
        .map<List<NewsModel>, NewsResult> { NewsResult.News(it) }
        .onStart { emit(NewsResult.Loading) }
        .catch { throwable ->
            Timber.e(throwable)
            emit(NewsResult.Error)
        }
}
