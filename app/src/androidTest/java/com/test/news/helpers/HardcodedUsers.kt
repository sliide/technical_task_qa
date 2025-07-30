package com.test.news.helpers

enum class HardcodedUsers(
    val userName: String,
    val password: String,
) {
    VALID_USER(
        userName = "user",
        password = "password",
    ),
    INVALID_USER(
        userName = "invalidUser",
        password = "invalidPassword",
    ),
    EMPTY_USER(
        userName = "",
        password = "",
    ),
}
