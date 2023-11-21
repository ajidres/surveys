package com.nimble.surveys.data.api.repositories.surveys

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.data.mapper.toSurveysResponseUI
import com.nimble.surveys.domain.model.SurveysResponseUI
import com.nimble.surveys.extentions.bodyOrException
import com.nimble.surveys.extentions.getErrorResponse

class SurveysRepositoryImpl  @Inject constructor(private val apis: EndPoints) : SurveysRepository{

    override fun surveys(): Flow<Result<SurveysResponseUI>> {
        return flow<Result<SurveysResponseUI>> {
            val response = apis.surveys().bodyOrException()
            emit(Result.Success(response.toSurveysResponseUI()))
        }.onStart {
            emit(Result.Loading(true))
        }.onCompletion {
            emit(Result.Loading(false))
        }.catch {
            emit(Result.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }


}