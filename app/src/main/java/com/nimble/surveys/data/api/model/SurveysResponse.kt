package com.nimble.surveys.data.api.model


import com.google.gson.annotations.SerializedName

data class SurveysResponse(
    @SerializedName("data")
    val `data`: List<DataSurveys>,
    @SerializedName("meta")
    val meta: Meta
)

data class Meta(
    @SerializedName("page")
    val page: Int,
    @SerializedName("page_size")
    val pageSize: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("records")
    val records: Int
)

data class DataSurveys(
    @SerializedName("attributes")
    val attributes: AttributesSurveys,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)

data class AttributesSurveys(
    @SerializedName("active_at")
    val activeAt: String,
    @SerializedName("cover_image_url")
    val coverImageUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("inactive_at")
    val inactiveAt: Any?,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("survey_type")
    val surveyType: String,
    @SerializedName("thank_email_above_threshold")
    val thankEmailAboveThreshold: String?,
    @SerializedName("thank_email_below_threshold")
    val thankEmailBelowThreshold: String?,
    @SerializedName("title")
    val title: String
)