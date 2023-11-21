package com.nimble.surveys.features.forgot

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.surveys.R
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.LoginResponseUI
import com.nimble.surveys.domain.useCases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.nimble.surveys.data.api.model.Result


class ForgotViewModel : ViewModel() {

    var email: String = ""

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    private var _userData = MutableLiveData<Result<LoginResponseUI>>()
    val userData: LiveData<Result<LoginResponseUI>> get() = _userData

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    data class UiState(
        val emailValid: Boolean = true,
    )


    fun onEmailChange(email: String) {
        this.email = email
        checkValues()
    }

    fun checkValues() {
        _uiState.value = _uiState.value.copy(
            emailValid = email.isNotEmpty() && EMAIL_ADDRESS.matcher(email).matches(),
        )
    }

    fun doReset() {
        if (_uiState.value.emailValid) {


        }
    }


}