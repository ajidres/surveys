package com.nimble.surveys.di

import com.nimble.surveys.data.api.EndPoints
import com.nimble.surveys.data.api.repositories.login.LoginRepository
import com.nimble.surveys.data.api.repositories.login.LoginRepositoryImpl
import com.nimble.surveys.data.api.repositories.surveys.SurveysRepository
import com.nimble.surveys.data.api.repositories.surveys.SurveysRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLoginRepository(apis: EndPoints): LoginRepository{
            return LoginRepositoryImpl(apis)
    }

    @Provides
    fun provideSurveyRepository(apis: EndPoints): SurveysRepository{
        return SurveysRepositoryImpl(apis)
    }

}