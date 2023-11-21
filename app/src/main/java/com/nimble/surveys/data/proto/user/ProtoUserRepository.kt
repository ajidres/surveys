package com.nimble.surveys.data.proto.user

import androidx.lifecycle.LiveData
import com.nimble.surveys.LoginItem
import com.nimble.surveys.domain.model.LoginResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoUserRepository {
    suspend fun getUserData(): Flow<LoginItem>

    suspend fun getUserDataInterceptor(): LoginItem
    suspend fun saveUserData(data: LoginResponseUI)

    suspend fun clearAllData()


}