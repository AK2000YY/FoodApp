package com.example.foodapp.di

import android.content.Context
import com.example.foodapp.core.constant.Constant.FOOD
import com.example.foodapp.core.constant.Constant.USER
import com.example.foodapp.data.repository.AuthRepositoryImpl
import com.example.foodapp.data.repository.FoodClassifierImpl
import com.example.foodapp.data.repository.FoodRepositoryImpl
import com.example.foodapp.data.repository.ImageUploadRepositoryImpl
import com.example.foodapp.domain.repository.AuthRepository
import com.example.foodapp.domain.repository.FoodClassifier
import com.example.foodapp.domain.repository.FoodRepository
import com.example.foodapp.domain.repository.ImageUploadRepository
import com.example.foodapp.presentation.cameraPreview.FoodCameraAnalyzer
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
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
    fun provideFoodRepository(): FoodRepository =
        FoodRepositoryImpl(
            collectionReference = Firebase
                .firestore
                .collection(USER)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(FOOD)
        )

    @Provides
    @Singleton
    fun provideImageUploadRepository(): ImageUploadRepository =
        ImageUploadRepositoryImpl(
            storageReference = FirebaseStorage
                .getInstance()
                .reference
                .child(Firebase.auth.currentUser!!.uid)
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