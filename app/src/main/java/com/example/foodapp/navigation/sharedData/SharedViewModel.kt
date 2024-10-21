package com.example.foodapp.navigation.sharedData

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Classification
import com.example.foodapp.presentation.cameraPreview.FoodCameraAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val foodCameraAnalyzer: FoodCameraAnalyzer
): ViewModel() {

    private val _state = MutableStateFlow(Classification())
    val state: StateFlow<Classification> = _state.asStateFlow()

    val controller = LifecycleCameraController(context)
        .apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS or CameraController.IMAGE_CAPTURE)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(
                    context
                ),
                foodCameraAnalyzer
            )
        }

    init {
        foodCameraAnalyzer.onResult = {
            if(it[0].name != null)
                updateName(it[0].name)
            else
                updateName(null)
        }
    }

    private fun updateImage(bitmap: Bitmap) {
        _state.update { currentState ->
            currentState.copy(
                image = bitmap
            )
        }
    }

    private fun updateName(name: String?) {
        _state.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }

    fun analyzeImage(bitmap: Bitmap) {
        foodCameraAnalyzer.analyzeImage(bitmap)
    }

    fun captureImage(
        toFoodView: () -> Unit
    ) {
        controller.takePicture(
            ContextCompat.getMainExecutor(context),
            object: OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )
                    updateImage(rotatedBitmap)
                    toFoodView()
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Utils.showMessage(context, exception.message)
                }
            }
        )

    }

}