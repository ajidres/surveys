package com.nimble.surveys.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)