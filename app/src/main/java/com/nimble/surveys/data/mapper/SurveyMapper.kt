package com.nimble.surveys.data.mapper

import com.nimble.surveys.data.api.model.SurveysResponse
import com.nimble.surveys.domain.model.SurveysItems
import com.nimble.surveys.domain.model.SurveysResponseUI
import com.nimble.surveys.SurveysList
fun SurveysResponse.toSurveysResponseUI(): SurveysResponseUI {
    return SurveysResponseUI(
        pageSize = meta.pageSize,
        surveys = data.map { SurveysItems(
            title = it.attributes.title,
            description = it.attributes.description,
            coverImageUrl = "${it.attributes.coverImageUrl}l"
        ) }.toList()
    )
}

fun SurveysList.toSurveysResponseUI(): SurveysResponseUI {
    return SurveysResponseUI(
        pageSize = pageSize,
        surveys = surveysList.map { SurveysItems(
            title = it.title,
            description = it.description,
            coverImageUrl = it.coverImageUrl
        ) }.toList()
    )
}