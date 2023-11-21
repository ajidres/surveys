package com.nimble.surveys.data.proto.user

import androidx.datastore.core.DataStore
import androidx.lifecycle.asLiveData
import com.nimble.surveys.LoginItem
import com.nimble.surveys.domain.model.LoginResponseUI
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class ProtoUserRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<LoginItem>
) : ProtoUserRepository {



    override suspend fun getUserData(): Flow<LoginItem> {
        return dataStore.data
    }

    override suspend fun getUserDataInterceptor(): LoginItem {
        return dataStore.data.first()
    }




    override suspend fun saveUserData(data: LoginResponseUI) {
        dataStore.updateData { loginPreferences: LoginItem ->
            loginPreferences.toBuilder().clear()
                .setAccessToken(data.accessToken)
                .setCreatedAt(data.createdAt)
                .setExpiresIn(data.expiresIn)
                .setRefreshToken(data.refreshToken)
                .setTokenType(data.tokenType)
                .build()
        }

    }

    override suspend fun clearAllData() {
        dataStore.updateData { loginPreferences: LoginItem ->
            loginPreferences.toBuilder().clear().build()
        }
    }

}