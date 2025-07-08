package com.test.news.di

import android.app.Application
import androidx.room.Room
import com.test.news.features.login.data.dao.UsersDao
import com.test.news.features.login.data.db.UsersDatabase
import com.test.news.features.news.data.dao.NewsDao
import com.test.news.features.news.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUsersDatabase(app: Application): UsersDatabase = Room
        .databaseBuilder(app, UsersDatabase::class.java, "users.db")
        .createFromAsset("users.db")
        .build()

    @Provides
    @Singleton
    fun provideUsersDao(database: UsersDatabase): UsersDao = database.usersDao()

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase = Room
        .databaseBuilder(app, NewsDatabase::class.java, "news.db")
        .createFromAsset("news.db")
        .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}
