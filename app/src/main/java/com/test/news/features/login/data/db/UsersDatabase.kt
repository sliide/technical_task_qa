package com.test.news.features.login.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.news.features.login.data.dao.UsersDao
import com.test.news.features.login.data.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false,
)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
