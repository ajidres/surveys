package com.nimble.surveys.domain.useCases

import com.nimble.surveys.LoginItem
import com.nimble.surveys.SurveysItems
import com.nimble.surveys.SurveysList
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.domain.model.SurveysResponseUI
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {

    suspend fun fetchUserData(): Flow<LoginItem>

    fun invokeSurveys(): Flow<Result<SurveysResponseUI>>
    suspend fun saveSurveysData(data: SurveysResponseUI)

    suspend fun fetchSurveyData():Flow<SurveysResponseUI>


}