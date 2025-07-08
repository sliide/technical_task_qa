package com.test.news.features.login.data.repository

import com.test.news.features.login.data.dao.UsersDao
import com.test.news.features.login.data.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UsersRepository
@Inject
constructor(
    private val usersDao: UsersDao,
) {
    fun getUser(userName: String) = usersDao.getUser(userName)

    fun getAllUsers(): Flow<List<User>> = usersDao.getAllUsers()
}
