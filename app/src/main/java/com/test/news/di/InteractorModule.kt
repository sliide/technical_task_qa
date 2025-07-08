package com.test.news.di

import com.test.news.features.login.data.repository.UsersRepository
import com.test.news.features.login.domain.repository.LoginInteractor
import com.test.news.features.news.domain.repository.GetNewsUseCase
import com.test.news.features.news.domain.repository.NewsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {
    @Provides
    fun provideLoginInteractor(usersRepository: UsersRepository): LoginInteractor = LoginInteractor(usersRepository)

    @Provides
    fun provideNewsInteractor(getNewsUseCase: GetNewsUseCase): NewsInteractor = NewsInteractor(getNewsUseCase)
}
