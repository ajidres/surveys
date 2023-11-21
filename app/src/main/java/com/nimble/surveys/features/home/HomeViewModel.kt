package com.nimble.surveys.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.surveys.LoginItem
import com.nimble.surveys.data.api.PREFIX_TOKEN
import com.nimble.surveys.data.api.model.RefreshTokenRequest
import com.nimble.surveys.domain.model.LoginRequestUI
import com.nimble.surveys.domain.model.SurveysResponseUI
import com.nimble.surveys.domain.useCases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import com.nimble.surveys.data.api.model.Result
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@HiltViewModel
class HomeViewModel@Inject constructor(private val homeUseCase: HomeUseCase):ViewModel() {

    private var _userData = MutableLiveData<LoginItem>()
    val userData: LiveData<LoginItem> get() = _userData

    private var _surveyData = MutableLiveData<SurveysResponseUI>()
    val surveyData: LiveData<SurveysResponseUI> get() = _surveyData

    private var _surveyDataApi = MutableLiveData<Result<SurveysResponseUI>>()
    val surveyDataApi: LiveData<Result<SurveysResponseUI>> get() = _surveyDataApi

    fun fetchData() {
        viewModelScope.launch {
            homeUseCase.fetchUserData().collect {
                _userData.value = it
            }
        }
    }

    fun fetchSurveysFromBD(){
        viewModelScope.launch {
            homeUseCase.fetchSurveyData().collect {
                _surveyData.value = it
            }
        }
    }

    fun fetchSurveysFromApi(){
        viewModelScope.launch {
            homeUseCase.invokeSurveys().collect {
                _surveyDataApi.value = it
            }
        }
    }

    fun saveData(data: SurveysResponseUI) {
        viewModelScope.launch {
            homeUseCase.saveSurveysData(data)
        }
    }

    fun fetchTodayDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        return formatter.format(time)
    }


}