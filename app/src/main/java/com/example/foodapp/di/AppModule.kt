package com.example.foodapp.di

import android.content.Context
import com.example.foodapp.data.repository.AuthRepositoryImpl
import com.example.foodapp.data.repository.FoodClassifierImpl
import com.example.foodapp.domain.repository.AuthRepository
import com.example.foodapp.domain.repository.FoodClassifier
import com.example.foodapp.presentation.cameraPreview.FoodImageAnalyzer
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository =
        AuthRepositoryImpl(Firebase.auth)

}


@Module
@InstallIn(ViewModelComponent::class)
class AppModuleForViewModel {

    @Provides
    fun provideFoodClassifier(
        @ApplicationContext context: Context,
    ): FoodClassifier =
        FoodClassifierImpl(
            context = context
        )

    @Provides
    fun provideFoodImageAnalyzer(
        foodClassifierImpl: FoodClassifierImpl
    ): FoodImageAnalyzer =
        FoodImageAnalyzer(
            classifier = foodClassifierImpl
        )

}