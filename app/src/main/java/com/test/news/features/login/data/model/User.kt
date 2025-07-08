package com.test.news.features.login.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userName: String,
    val password: String,
    val isPremium: Boolean,
)
