package com.nimble.surveys.domain.model


data class SurveysResponseUI (
    val pageSize: Int,
    val surveys: List<SurveysItems>,

)

data class SurveysItems(
    val title: String,
    val description: String,
    val coverImageUrl: String,
)