package com.nimble.surveys.domain.model


data class LoginResponseUI (

    val accessToken: String,
    val createdAt: Int,
    val expiresIn: Int,
    val refreshToken: String,
    val tokenType: String

)