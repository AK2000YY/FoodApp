package com.example.foodapp.domain.repository

import coil3.Bitmap
import com.example.foodapp.domain.model.Classification

interface FoodClassifier {

    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>

}