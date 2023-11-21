package com.nimble.surveys.data.api.repositories.login

import com.nimble.surveys.data.api.model.LoginRequest
import com.nimble.surveys.data.api.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.domain.model.LoginResponseUI

interface LoginRepository {

    fun login(body: LoginRequest): Flow<Result<LoginResponseUI>>

}