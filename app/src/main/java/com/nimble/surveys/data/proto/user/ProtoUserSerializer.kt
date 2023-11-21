package com.nimble.surveys.data.proto.user

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.nimble.surveys.LoginItem
import java.io.InputStream
import java.io.OutputStream

object ProtoUserSerializer : Serializer<LoginItem> {
    override val defaultValue: LoginItem = LoginItem.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): LoginItem {
        try {
            return LoginItem.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }
    override suspend fun writeTo(t: LoginItem, output: OutputStream) = t.writeTo(output)
}

val Context.loginDataStore: DataStore<LoginItem> by dataStore(
    fileName = "user_prefs.pb",
    serializer = ProtoUserSerializer
)