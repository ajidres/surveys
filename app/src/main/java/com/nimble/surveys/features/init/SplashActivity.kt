package com.nimble.surveys.features.init

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        val time= TimeUnit.SECONDS.toMillis(2)
        object : CountDownTimer(time, time) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }.start()

    }
}
