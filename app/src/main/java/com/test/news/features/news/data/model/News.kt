package com.test.news.features.news.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val datatype: String?,
    val title: String?,
    val author: String?,
    @ColumnInfo(name = "source_name") val sourceName: String?,
    @ColumnInfo(name = "deep_link") val deepLink: String?,
    val url: String?,
    val publishedAt: String?,
    val content: String?,
    val description: String?,
    val type: String?,
)

@Entity(
    tableName = "images",
    foreignKeys = [
        ForeignKey(
            entity = News::class,
            parentColumns = ["id"],
            childColumns = ["news_id"],
        ),
    ],
    indices = [Index(value = ["news_id"])],
)
data class Image(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "news_id") val newsId: Int,
    val url: String?,
)

data class NewsWithImages(
    @Embedded val news: News,
    @Relation(
        parentColumn = "id",
        entityColumn = "news_id",
    )
    val images: List<Image>,
)
