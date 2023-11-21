package com.nimble.surveys.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class RefreshTokenRequest(
    @SerializedName("refresh_token")
    val refreshToken: String,
)