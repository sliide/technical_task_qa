package com.test.news.test

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.agoda.kakao.recycler.KRecyclerView
import com.test.news.R
import com.test.news.base.BaseTest
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.features.news.presentation.adapter.NewsImagesAdapter
import com.test.news.screen.LoginScreen
import com.test.news.screen.NewsScreen
import com.test.news.test.TestData.VALID_USER_NAME
import com.test.news.test.TestData.VALID_USER_PASSWORD
import org.hamcrest.Matchers.allOf
import org.junit.Test

class NewsTest : BaseTest<LoginActivity>(LoginActivity::class.java) {

    @Test
    fun news_images_displayed_in_list() {
        run {
            step("Given the app is installed") {}
            step("When the user logs in") {
                LoginScreen {
                    logIn(
                        userName = VALID_USER_NAME,
                        password = VALID_USER_PASSWORD
                    )
                }
            }
            step("Then images in the list are displayed") {
                NewsScreen {
                    newsRecycler {
                        isDisplayed()
                        imagesDisplayedInRows()
                    }
                }
            }
        }
    }

    @Test
    fun no_internet_error_displayed() {
        testCaseRule
            .before {}
            .after {
                enableConnection()
            }.run {
                step("Given internet connection is interrupted") {
                    disableConnection()
                }
                step("When the user logs in") {
                    LoginScreen {
                        logIn(
                            userName = VALID_USER_NAME,
                            password = VALID_USER_PASSWORD
                        )
                    }
                }
                step("Then error is displayed") {
                    NewsScreen {
                        errorText {
                            isDisplayed()
                            hasText(R.string.news_failed_to_load_message)
                        }
                    }
                }
            }
    }

    @Test
    fun click_image_redirects_to_browser() {
        testCaseRule
            .before { Intents.init() }
            .after { Intents.release() }
            .run {
                var url = ""
                step("Given the user is on news screen") {
                    LoginScreen {
                        logIn(
                            userName = VALID_USER_NAME,
                            password = VALID_USER_PASSWORD
                        )
                    }
                    NewsScreen {
                        isDisplayed()
                    }
                }
                step("When the user clicks first image") {
                    NewsScreen {
                        newsRecycler {
                            url = getFirstUrl()
                            clickFirst()
                        }
                    }
                }
                step("Then external browser is opened") {
                    intended(
                        allOf(
                            hasData(url),
                            hasAction(Intent.ACTION_VIEW)
                        )
                    )
                }
            }
    }

    private fun disableConnection() {
        getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
    }

    private fun enableConnection() {
        getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
    }

    private fun KRecyclerView.getFirstUrl(): String {
        var url = ""
        firstChild<NewsScreen.RecyclerItem> {
            imageItem {
                url = getUrl(position = 0)
            }
        }
        return url
    }

    private fun KRecyclerView.getUrl(position: Int): String {
        var url = ""

        view.perform(object : ViewAction {
            override fun getDescription() = "Get RecyclerView adapter size"

            override fun getConstraints() = allOf(
                ViewMatchers.isAssignableFrom(
                    RecyclerView::class.java
                ), ViewMatchers.isDisplayed()
            )

            override fun perform(uiController: UiController?, view: View?) {
                if (view is RecyclerView) {
                    val adapter = view.adapter!! as NewsImagesAdapter
                    url = adapter.getImageUrls()[position]
                }
            }
        })

        return url
    }

    private fun KRecyclerView.clickFirst() {
        firstChild<NewsScreen.RecyclerItem> {
            imageItem {
                firstChild<NewsScreen.ImageItem> {
                    image {
                        click()
                    }
                }
            }
        }
    }

    private fun KRecyclerView.imagesDisplayedInRows() {
        children<NewsScreen.RecyclerItem> {
            imageItem {
                children<NewsScreen.ImageItem> {
                    image {
                        isDisplayed()
                    }
                }
            }
        }
    }
}
