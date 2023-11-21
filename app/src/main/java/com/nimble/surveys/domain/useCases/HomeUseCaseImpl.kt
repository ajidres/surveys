package com.nimble.surveys.domain.useCases

import com.nimble.surveys.LoginItem
import com.nimble.surveys.data.api.model.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.repositories.login.LoginRepository
import com.nimble.surveys.data.api.repositories.surveys.SurveysRepository
import com.nimble.surveys.data.mapper.toLoginRequest
import com.nimble.surveys.data.proto.surveys.ProtoSurveysRepository
import com.nimble.surveys.data.proto.user.ProtoUserRepository
import com.nimble.surveys.domain.model.SurveysResponseUI

class HomeUseCaseImpl @Inject constructor(private val apiRepository: SurveysRepository,
                                          private val bdUserRepository: ProtoUserRepository,
                                          private val bdSurveyRepository: ProtoSurveysRepository
) :
    HomeUseCase {

    override suspend fun fetchUserData(): Flow<LoginItem> = bdUserRepository.getUserData()
    override fun invokeSurveys(): Flow<Result<SurveysResponseUI>> = apiRepository.surveys()

    override suspend fun saveSurveysData(data: SurveysResponseUI)  =  bdSurveyRepository.saveSurveysData(data)

    override suspend fun fetchSurveyData()  =  bdSurveyRepository.getSurveysData()


}