package com.example.foodapp.presentation.cameraPreview

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.mutableStateOf
import com.example.foodapp.core.extensionFunction.centerCrop
import com.example.foodapp.domain.model.Classification
import com.example.foodapp.domain.repository.FoodClassifier
import javax.inject.Inject

class FoodImageAnalyzer @Inject constructor(
    private val classifier: FoodClassifier
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    var classification: List<Classification> = emptyList()
        private set


    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegrees)
//            onResults(results)
            classification = results
        }
        frameSkipCounter++

        image.close()
    }
}