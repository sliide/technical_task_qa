package com.test.news.di

import com.test.news.features.login.data.dao.UsersDao
import com.test.news.features.login.data.repository.UsersRepository
import com.test.news.features.news.data.dao.NewsDao
import com.test.news.features.news.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUsersRepository(usersDao: UsersDao): UsersRepository = UsersRepository(usersDao)

    @Provides
    @Singleton
    fun provideNewsRepository(newsDao: NewsDao): NewsRepository = NewsRepository(newsDao)
}
