package com.test.news.features.news.domain.model

import com.test.news.features.news.data.model.NewsWithImages
import javax.inject.Inject

class NewsItemsModelMapper
@Inject
constructor() {
    fun map(newsItems: List<NewsWithImages>) = mutableListOf<NewsModel>()
        .apply {
            newsItems.forEach { add(mapNewsItem(it)) }
        }.toList()

    private fun mapNewsItem(item: NewsWithImages) = when (item.news.type) {
        "slider" ->
            NewsModel.Slider(
                item.images.mapNotNull { it.url },
                item.news.datatype,
                item.news.title,
                item.news.author,
                item.news.sourceName,
                item.news.deepLink,
                item.news.url,
                item.news.publishedAt,
                item.news.content,
                item.news.description,
            )

        "image" ->
            NewsModel.Image(
                item.images.mapNotNull { it.url },
                item.news.datatype,
                item.news.title,
                item.news.author,
                item.news.sourceName,
                item.news.deepLink,
                item.news.url,
                item.news.publishedAt,
                item.news.content,
                item.news.description,
            )

        else -> NewsModel.Empty()
    }
}
