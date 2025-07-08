package com.test.news.features.news.domain.view

import ImageNewsItem
import SliderNewsItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.news.R
import com.test.news.features.news.domain.model.NewsModel
import com.test.news.features.news.domain.repository.NewsResult
import com.test.news.features.news.domain.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val newsResult by viewModel.newsState.collectAsState()

    when (newsResult) {
        is NewsResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is NewsResult.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.news_failed_to_load_message))
            }
        }

        is NewsResult.News -> {
            val newsList = (newsResult as NewsResult.News).news
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(newsList) { newsModel ->
                    when (newsModel) {
                        is NewsModel.Image -> ImageNewsItem(newsModel)
                        is NewsModel.Slider -> SliderNewsItem(newsModel)
                        is NewsModel.Empty -> {}
                    }
                }
            }
        }
    }
}
