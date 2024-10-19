package com.example.foodapp.presentation.cameraPreview

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.foodapp.domain.model.Classification
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val foodImageAnalyzer: FoodImageAnalyzer
): ViewModel() {

    var classification by mutableStateOf(emptyList<Classification>())
        private set

    val controller = LifecycleCameraController(context)
        .apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(
                    context
                ),
                foodImageAnalyzer
            )
        }

    init {
        foodImageAnalyzer.onResult = {
            classification = it
        }
    }

}