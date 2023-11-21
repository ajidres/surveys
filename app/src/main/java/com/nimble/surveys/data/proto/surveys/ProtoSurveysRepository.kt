package com.nimble.surveys.data.proto.surveys

import com.nimble.surveys.LoginItem
import com.nimble.surveys.SurveysList
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.domain.model.SurveysResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoSurveysRepository {


    suspend fun getSurveysData(): Flow<SurveysResponseUI>
    suspend fun saveSurveysData(data: SurveysResponseUI)
    suspend fun clearAllData()




}