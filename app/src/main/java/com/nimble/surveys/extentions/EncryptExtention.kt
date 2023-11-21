package com.nimble.surveys.extentions

import android.util.Base64
import com.nimble.surveys.BuildConfig
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

import kotlin.io.encoding.ExperimentalEncodingApi

val key = SecretKeySpec(BuildConfig.ENCRYPT_KEY.toByteArray(), "AES")
val iv = IvParameterSpec(ByteArray(16))

fun String.encrypt(): String {

    val cipher = Cipher.getInstance(BuildConfig.ENCRYPT_ALGORITHM)
    cipher.init(Cipher.ENCRYPT_MODE, key, iv)
    val cipherText = cipher.doFinal(this.toByteArray())
    return Base64.encodeToString(cipherText, Base64.NO_WRAP)

}

fun String.decrypt(): String {

    val cipher = Cipher.getInstance(BuildConfig.ENCRYPT_ALGORITHM)
    cipher.init(Cipher.DECRYPT_MODE, key, iv)
    val plainText = cipher.doFinal(Base64.decode(this, Base64.NO_WRAP))
    return String(plainText)

}