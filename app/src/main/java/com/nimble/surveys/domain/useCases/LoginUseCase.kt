package com.nimble.surveys.domain.useCases


import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.LoginResponseUI

interface LoginUseCase {

    fun invokeLogin(body: LoginRequestUI): Flow<Result<LoginResponseUI>>
    suspend fun saveUserData(data: LoginResponseUI)



}