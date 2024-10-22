package com.example.foodapp.di

import android.content.Context
import com.example.foodapp.core.constant.Constant.FOOD
import com.example.foodapp.data.repository.AuthRepositoryImpl
import com.example.foodapp.data.repository.FoodClassifierImpl
import com.example.foodapp.data.repository.FoodRepositoryImpl
import com.example.foodapp.domain.repository.AuthRepository
import com.example.foodapp.domain.repository.FoodClassifier
import com.example.foodapp.presentation.cameraPreview.FoodCameraAnalyzer
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
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

    @Provides
    @Singleton
    fun provideFoodRepository(): FoodRepositoryImpl =
        FoodRepositoryImpl(
            collectionReference = Firebase.firestore.collection(FOOD)
        )

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
    fun provideFoodCameraAnalyzer(
        foodClassifier: FoodClassifier
    ): FoodCameraAnalyzer =
        FoodCameraAnalyzer(
            classifier = foodClassifier
        )


}