package com.example.android_challenge_1.utils

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
    return emailRegex.matches(email)
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$".toRegex()
    return passwordRegex.matches(password)
}