package com.example.foodapp.navigation.sharedData

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Classification
import com.example.foodapp.domain.model.Food
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.model.Response.Success
import com.example.foodapp.domain.repository.FoodRepository
import com.example.foodapp.domain.repository.ImageUploadRepository
import com.example.foodapp.presentation.cameraPreview.FoodCameraAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val foodCameraAnalyzer: FoodCameraAnalyzer,
    private val foodRepository: FoodRepository,
    private val imageUploadRepository: ImageUploadRepository
): ViewModel() {

    var response by mutableStateOf<Response<Boolean>>(Success(false))
        private set

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
            updateName(it[0].name)
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

    fun analyzeImage(
        bitmap: Bitmap,
        toFoodView: () -> Unit
    ) {
        foodCameraAnalyzer.analyzeImage(bitmap)
        if(state.value.name != null) {
            updateImage(bitmap)
            toFoodView()
        }else
            Utils.showMessage(context, "don't know the image")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveFood() = viewModelScope.launch {
        response = Response.Loading
        val responseImage = imageUploadRepository.uploadImage(state.value.image!!)
        response = if(responseImage is Success && responseImage.data != "")
            foodRepository.addFood(
                Food(
                    name = state.value.name,
                    image = responseImage.data,
                    date = LocalDate.now().toString(),
                    favour = false
                )
            )
        else Response.Failure(Exception("something is error"))
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