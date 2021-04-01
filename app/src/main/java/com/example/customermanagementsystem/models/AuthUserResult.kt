package com.example.customermanagementsystem.models

data class AuthUserResult(
    val accessToken: String,
    val refreshExpirationTime: String,
    val refreshToken: String,
    val tokenExpirationTime: String
)