package com.nimble.surveys.features.login

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-])(?=\\S+$).{5,}$".toRegex()

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    var email: String = ""
    var password: String = ""

    private var _userData = MutableLiveData<Result<LoginResponseUI>>()
    val userData: LiveData<Result<LoginResponseUI>> get() = _userData

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    data class UiState(
        val emailValid: Boolean = true,
        val passwordValid: Boolean = true,
    )

    fun onPasswordChange(password: String) {
        this.password = password
        checkValues()
    }

    fun onEmailChange(email: String) {
        this.email = email
        checkValues()
    }

    fun checkValues() {
        _uiState.value = _uiState.value.copy(
            emailValid = email.isNotEmpty() && EMAIL_ADDRESS.matcher(email).matches(),
            passwordValid = password.isNotEmpty() && password.matches(PASSWORD_PATTERN)
        )
    }

    fun doLogin() {
        if (_uiState.value.passwordValid && _uiState.value.emailValid) {
            viewModelScope.launch {

                loginUseCase.invokeLogin(
                    LoginRequestUI(
                        email = email,
                        password = password
                    )
                ).collect {
                    _userData.value = it
                }

            }

        }
    }

    fun saveData(data: LoginResponseUI) {
        viewModelScope.launch {
            loginUseCase.saveUserData(data)
        }
    }



}