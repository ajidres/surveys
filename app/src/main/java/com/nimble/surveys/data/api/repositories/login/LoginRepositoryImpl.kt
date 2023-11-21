package com.nimble.surveys.data.api.repositories.login

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.EndPoints
import com.nimble.surveys.data.mapper.toLoginResponseUI
import com.nimble.surveys.data.api.model.LoginRequest
import com.nimble.surveys.data.api.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.extentions.bodyOrException
import com.nimble.surveys.extentions.getErrorResponse

class LoginRepositoryImpl  @Inject constructor(private val apis: EndPoints) : LoginRepository{

    override fun login(body: LoginRequest): Flow<Result<LoginResponseUI>> {
        return flow<Result<LoginResponseUI>> {
            val response = apis.login(body=body).bodyOrException()
            emit(Result.Success(response.toLoginResponseUI()))
        }.onStart {
            emit(Result.Loading(true))
        }.onCompletion {
            emit(Result.Loading(false))
        }.catch {
            emit(Result.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }




}