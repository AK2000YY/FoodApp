package com.example.foodapp.presentation.cameraPreview

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.foodapp.core.extensionFunction.centerCrop
import com.example.foodapp.domain.model.Classification
import com.example.foodapp.domain.repository.FoodClassifier
import javax.inject.Inject

class FoodImageAnalyzer @Inject constructor(
    private val classifier: FoodClassifier
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    var onResult: (List<Classification>) -> Unit = {}

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(224, 224)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResult(results)
        }
        frameSkipCounter++

        image.close()
    }
}