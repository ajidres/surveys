package com.nimble.surveys.data.api.repositories.surveys

import kotlinx.coroutines.flow.Flow
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.data.api.model.SurveysResponse
import com.nimble.surveys.domain.model.SurveysResponseUI

interface SurveysRepository {

    fun surveys(): Flow<Result<SurveysResponseUI>>


}