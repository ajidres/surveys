package com.nimble.surveys.data.api

import com.nimble.surveys.BuildConfig
import com.nimble.surveys.LoginItem
import com.nimble.surveys.data.api.model.RefreshTokenRequest
import com.nimble.surveys.data.mapper.toLoginResponseUI
import com.nimble.surveys.data.proto.user.ProtoUserRepository
import com.nimble.surveys.extentions.decrypt
import java.util.Calendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthInterceptor (
    private val user: ProtoUserRepository,
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val result = runBlocking{
            checkExpiration(originalRequest)
        }

        return chain.proceed(result)

    }

    private suspend fun checkExpiration(request: Request): Request {

        val time = Calendar.getInstance().time.time
        var expired = 0

        val data = CoroutineScope(Dispatchers.IO).async{
            return@async user.getUserDataInterceptor()
        }.await()

        if(data.createdAt>0){
            expired = (time / data.createdAt).toInt()
        }

        return if (data.accessToken.isNotEmpty() && expired > data.expiresIn) {
            tokeExpired(data, request)
        }else{
            request.newBuilder()
                .header(AUTHORIZATION, "$PREFIX_TOKEN ${data.accessToken.decrypt()}")
                .build()
        }

    }

    private fun tokeExpired(loginItem: LoginItem, originalRequest: Request): Request {

        val apis = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT_URL)
            .build()
            .create(EndPoints::class.java)

        val refreshedToken = runBlocking {
            val response = apis.refresh(body = RefreshTokenRequest(loginItem.refreshToken!!))
            val data =response.toLoginResponseUI()
            user.saveUserData(data)
            response.data.attributes.refreshToken
        }

        return originalRequest.newBuilder()
            .header(AUTHORIZATION, "$PREFIX_TOKEN $refreshedToken")
            .build()

    }


}