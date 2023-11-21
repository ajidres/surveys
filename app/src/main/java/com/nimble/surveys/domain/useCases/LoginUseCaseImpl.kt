package com.nimble.surveys.domain.useCases

import com.nimble.surveys.data.mapper.toLoginRequest
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.repositories.login.LoginRepository
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.data.proto.user.ProtoUserRepository

class LoginUseCaseImpl @Inject constructor(private val apiRepository: LoginRepository,
                                           private val bdRepository: ProtoUserRepository
) :
    LoginUseCase {


    override fun invokeLogin(body: LoginRequestUI): Flow<Result<LoginResponseUI>> = apiRepository.login(body.toLoginRequest())
    override suspend fun saveUserData(data: LoginResponseUI) = bdRepository.saveUserData(data)


}