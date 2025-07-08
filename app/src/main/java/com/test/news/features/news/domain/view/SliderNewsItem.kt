import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.test.news.R
import com.test.news.features.news.domain.model.NewsModel

@Composable
fun SliderNewsItem(newsModel: NewsModel.Slider) {
    val context = LocalContext.current
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                newsModel.url?.let {
                    val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                    context.startActivity(intent)
                }
            },
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            val pagerState = rememberPagerState { newsModel.imageUrlsList.size }

            HorizontalPager(state = pagerState) { page ->
                val imageUrl = newsModel.imageUrlsList[page]
                val painter = rememberAsyncImagePainter(model = imageUrl)

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            if (newsModel.imageUrlsList.size > 1) {
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier =
                            Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(16.dp),
                        )
                    }
                }
            }

            newsModel.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            newsModel.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            newsModel.author?.let {
                Text(
                    text = stringResource(R.string.news_item_author_prefix) + " " + it,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            newsModel.sourceName?.let {
                Text(
                    text = stringResource(R.string.news_item_source_prefix) + " " + it,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
    }
}
