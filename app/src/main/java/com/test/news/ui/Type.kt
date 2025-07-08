package com.test.news.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.test.news.R

val Sriracha =
    FontFamily(
        Font(R.font.sriracha),
    )

val Typography =
    Typography(
        displayLarge =
        TextStyle(
            fontFamily = Sriracha,
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
        ),
    )
