package com.nimble.surveys.data.proto.surveys

import androidx.datastore.core.DataStore
import com.nimble.surveys.SurveysList
import com.nimble.surveys.SurveysItems
import com.nimble.surveys.data.mapper.toSurveysResponseUI

import com.nimble.surveys.domain.model.SurveysResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoSurveysRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<SurveysList>
) : ProtoSurveysRepository {


    override suspend fun getSurveysData(): Flow<SurveysResponseUI> {
        return dataStore.data.map { it.toSurveysResponseUI() }
    }

    override suspend fun saveSurveysData(data: SurveysResponseUI) {

        dataStore.updateData { surveysPreferences: SurveysList ->

            surveysPreferences.toBuilder().clear()
                .setPageSize(data.pageSize)
                .addAllSurveys(data.surveys.map {
                    SurveysItems.newBuilder()
                        .setCoverImageUrl(it.coverImageUrl)
                        .setTitle(it.title)
                        .setDescription(it.description)
                        .build()
                }.toList())
                .build()
        }
    }

    override suspend fun clearAllData() {
        dataStore.updateData { surveysPreferences: SurveysList ->
            surveysPreferences.toBuilder().clear().build()
        }
    }
}