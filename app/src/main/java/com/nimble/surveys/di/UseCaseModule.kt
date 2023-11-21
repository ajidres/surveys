package com.nimble.surveys.di

import com.nimble.surveys.data.api.repositories.login.LoginRepository
import com.nimble.surveys.data.api.repositories.surveys.SurveysRepository
import com.nimble.surveys.data.proto.surveys.ProtoSurveysRepository
import com.nimble.surveys.data.proto.user.ProtoUserRepository
import com.nimble.surveys.domain.useCases.HomeUseCase
import com.nimble.surveys.domain.useCases.HomeUseCaseImpl
import com.nimble.surveys.domain.useCases.LoginUseCase
import com.nimble.surveys.domain.useCases.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        bdRepository: ProtoUserRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(loginRepository, bdRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideHomeUseCase(
        surveysRepository: SurveysRepository,
        bdUserRepository: ProtoUserRepository,
        bdSurveyRepository: ProtoSurveysRepository
    ): HomeUseCase {
        return HomeUseCaseImpl(surveysRepository, bdUserRepository, bdSurveyRepository)
    }


}