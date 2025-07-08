package com.test.news.features.news.domain.model

sealed class NewsModel(
    open val imageUrlsList: List<String>? = null,
    open val datatype: String? = null,
    open val title: String? = null,
    open val author: String? = null,
    open val sourceName: String? = null,
    open val deepLink: String? = null,
    open val url: String? = null,
    open val publishedAt: String? = null,
    open val content: String? = null,
    open val description: String? = null,
) {
    data class Image(
        override val imageUrlsList: List<String>,
        override val datatype: String?,
        override val title: String?,
        override val author: String?,
        override val sourceName: String?,
        override val deepLink: String?,
        override val url: String?,
        override val publishedAt: String?,
        override val content: String?,
        override val description: String?,
    ) : NewsModel(
        imageUrlsList,
        datatype,
        title,
        author,
        sourceName,
        deepLink,
        url,
        publishedAt,
        content,
        description,
    )

    data class Slider(
        override val imageUrlsList: List<String>,
        override val datatype: String?,
        override val title: String?,
        override val author: String?,
        override val sourceName: String?,
        override val deepLink: String?,
        override val url: String?,
        override val publishedAt: String?,
        override val content: String?,
        override val description: String?,
    ) : NewsModel(
        imageUrlsList,
        datatype,
        title,
        author,
        sourceName,
        deepLink,
        url,
        publishedAt,
        content,
        description,
    )

    class Empty : NewsModel()
}
