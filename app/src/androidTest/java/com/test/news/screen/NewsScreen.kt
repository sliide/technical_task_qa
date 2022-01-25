package com.test.news.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.test.news.R
import org.hamcrest.Matcher

object NewsScreen : BaseScreen<NewsScreen>() {
    val newsRecycler = KRecyclerView(
        builder = { withId(R.id.recyclerViewNews) },
        itemTypeBuilder = { itemType(::RecyclerItem) }
    )
    override val specificView = newsRecycler
    val errorText = KTextView { withId(R.id.textViewError) }

    class RecyclerItem(parent: Matcher<View>) : KRecyclerItem<RecyclerItem>(parent) {
        val imageItem = KRecyclerView(
            builder = {
                withMatcher(parent)
                withId(R.id.recyclerViewImageWidget)
            },
            itemTypeBuilder = { itemType(::ImageItem) })
    }

    class ImageItem(parent: Matcher<View>) : KRecyclerItem<ImageItem>(parent) {
        val image = KImageView(parent) { withId(R.id.imageView) }
    }
}