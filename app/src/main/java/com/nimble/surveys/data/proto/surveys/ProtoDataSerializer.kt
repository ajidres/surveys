package com.nimble.surveys.data.proto.surveys

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.nimble.surveys.LoginItem
import com.nimble.surveys.SurveysList
import java.io.InputStream
import java.io.OutputStream

object ProtoSurveysSerializer : Serializer<SurveysList> {
    override val defaultValue: SurveysList = SurveysList.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): SurveysList {
        try {
            return SurveysList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }
    override suspend fun writeTo(t: SurveysList, output: OutputStream) = t.writeTo(output)
}

val Context.surveysDataStore: DataStore<SurveysList> by dataStore(
    fileName = "survey_prefs.pb",
    serializer = ProtoSurveysSerializer
)