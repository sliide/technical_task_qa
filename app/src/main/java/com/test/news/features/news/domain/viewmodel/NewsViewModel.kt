package com.test.news.features.news.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.news.features.news.domain.repository.NewsInteractor
import com.test.news.features.news.domain.repository.NewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class NewsViewModel
@Inject
constructor(
    private val newsInteractor: NewsInteractor,
) : ViewModel() {
    private val _newsState = MutableStateFlow<NewsResult>(NewsResult.Loading)
    val newsState: StateFlow<NewsResult> = _newsState

    init {
        getNews()
    }

    private fun getNews() {
        newsInteractor
            .getNews()
            .onEach {
                _newsState.value = it
            }.launchIn(viewModelScope)
    }
}
