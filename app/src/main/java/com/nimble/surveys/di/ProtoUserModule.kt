package com.nimble.surveys.di

import android.content.Context
import com.nimble.surveys.data.proto.surveys.ProtoSurveysRepository
import com.nimble.surveys.data.proto.surveys.ProtoSurveysRepositoryImpl
import com.nimble.surveys.data.proto.surveys.surveysDataStore

import com.nimble.surveys.data.proto.user.ProtoUserRepository
import com.nimble.surveys.data.proto.user.ProtoUserRepositoryImpl
import com.nimble.surveys.data.proto.user.loginDataStore
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProtoUserModule {

    @Provides
    @Reusable
    internal fun providesUserDataRepository(
        @ApplicationContext context: Context,
    ): ProtoUserRepository {
        return ProtoUserRepositoryImpl(
            context.loginDataStore
        )
    }

    @Provides
    @Reusable
    internal fun providesSurveysDataRepository(
        @ApplicationContext context: Context,
    ): ProtoSurveysRepository {
        return ProtoSurveysRepositoryImpl(
            context.surveysDataStore
        )
    }

}