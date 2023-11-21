package com.nimble.surveys.data.api.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.serialization.Serializable


data class LoginResponse(
    @SerializedName("data")
    val data: Data
)


data class Data(
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)


data class Attributes(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String
)