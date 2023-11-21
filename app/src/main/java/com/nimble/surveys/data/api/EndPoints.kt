package com.nimble.surveys.data.api

import com.nimble.surveys.BuildConfig.CLIENT_ID
import com.nimble.surveys.BuildConfig.CLIENT_SECRET
import com.nimble.surveys.data.api.model.LoginRequest
import com.nimble.surveys.data.api.model.LoginResponse
import com.nimble.surveys.data.api.model.RefreshTokenRequest
import com.nimble.surveys.data.api.model.SurveysResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

const val CLIENT_SECRET_QUERY ="client_secret"
const val CLIENT_ID_QUERY="client_id"
const val GRANT_TYPE="grant_type"
const val GRANT_TYPE_LOGIN="password"
const val GRANT_TYPE_REFRESH="refresh_token"
const val AUTHORIZATION = "Authorization"
const val PREFIX_TOKEN="Bearer"

interface EndPoints {

    @POST("oauth/token")
    suspend fun login(@Query(CLIENT_SECRET_QUERY) clientSecret:String = CLIENT_SECRET,
                      @Query(CLIENT_ID_QUERY) clientID:String = CLIENT_ID,
                      @Query(GRANT_TYPE) grantType:String = GRANT_TYPE_LOGIN,
                      @Body body: LoginRequest): Response<LoginResponse>

    @POST("oauth/token")
    suspend fun refresh(@Query(GRANT_TYPE) grantType:String = GRANT_TYPE_REFRESH,
                      @Body body: RefreshTokenRequest): LoginResponse

    @GET("surveys")
    suspend fun surveys(): Response<SurveysResponse>


}
